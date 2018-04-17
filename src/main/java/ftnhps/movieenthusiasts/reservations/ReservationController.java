package ftnhps.movieenthusiasts.reservations;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnhps.movieenthusiasts.users.User;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	@Autowired
	private HttpSession session;
	@Autowired
	private ReservationConverter reservationConverter;
	
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
	
	@PostMapping
	public ResponseEntity<List<Reservation>> add(@RequestBody ReservationDTO input)
	{
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Reservation> reservations = reservationConverter.fromDTO(input, user);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		reservations = reservationService.add(reservations);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
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
}
