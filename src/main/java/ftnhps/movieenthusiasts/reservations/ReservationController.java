package ftnhps.movieenthusiasts.reservations;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnhps.movieenthusiasts.EmailUtils;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserType;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;
	@Autowired
	private ReservationConverter reservationConverter;
	@Autowired 
	private EmailUtils email;
	
	@GetMapping
	public ResponseEntity<List<Reservation>> getAll() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Reservation> reservations = reservationService.findByUser(user);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@GetMapping("/{type:future|history}")
	public ResponseEntity<List<Reservation>> getAll(@PathVariable String type) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Reservation> reservations = null;
		if(type.equals("history"))
			reservations = reservationService.findHistory(user);
		else
			reservations = reservationService.findFuture(user);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@GetMapping("/fast/{id:\\d+}")
	public ResponseEntity<List<Reservation>> getFastReservations(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//		if(user.getUserType() != UserType.VISITOR)
//			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Reservation> reservations = reservationService.findFastReservations(id);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<List<Reservation>> add(@RequestBody ReservationDTO input) 
			throws MailException, InterruptedException {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Reservation> reservations = reservationConverter.fromDTO(input, user);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		reservations = reservationService.add(reservations);
		
		for(Reservation reservation : reservations) {
			email.sendEmail(reservation.getUser(),
					"Reservations on Movie Enthusiasts",
					"You have a new reservation for " 
					+ reservation.getDateTime().getProjection().getName()
					+ "\n\nCancel: "
					+ "/api/reservations/remove/" + reservation.getId());
		}
		
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@PostMapping("/fast")
	public ResponseEntity<List<Reservation>> addFast(@RequestBody ReservationDTO input)
	{
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.PLACEADMIN)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Reservation> reservations = reservationConverter.fromDTOFast(input, user);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		//null the users and add discount
		for(Reservation r:reservations) {
			r.setUser(null);
			if(input.getDiscount() >= 0 && input.getDiscount() <= 100)
				r.setDicount(input.getDiscount());
			else
				r.setDicount(0);
		}
		reservations = reservationService.add(reservations);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@PostMapping("/rate/{id:\\d+}")
	public ResponseEntity<Reservation> rate(@PathVariable Long id,@RequestBody RateDTO input)
	{
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Reservation reservation = reservationService.findOne(id);
		if(reservation == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
		reservation = reservationService.rate(reservation,input,user);
		if(reservation == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	@PutMapping("/reserveFast/{id:\\d+}")
	public ResponseEntity<Reservation> reserveFast(@PathVariable Long id){
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Reservation reservation = reservationService.findOne(id);
		if(reservation == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		reservation.setUser(user);
		reservationService.edit(id, reservation);
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id:\\d+}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		if(reservationService.remove(id, user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/remove/{id:\\d+}")
	public ResponseEntity<?> removeWithGet(@PathVariable Long id) {
		return remove(id);
	}
}
