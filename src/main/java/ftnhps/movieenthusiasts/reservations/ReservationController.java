package ftnhps.movieenthusiasts.reservations;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionService;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private ReservationService reservationService;
	@Autowired
	private PlaceService placeService;
	
	@GetMapping
	public ResponseEntity<List<Reservation>> getReservations(){
		List<Reservation> reservations = reservationService.findAll();
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reservations,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Reservation> getReservations(@PathVariable Long id){
		Reservation reservations = reservationService.findOne(id);
		if(reservations == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reservations,HttpStatus.OK);
	}
	
	@GetMapping("/projection/{id:\\d+}")
	public ResponseEntity<List<Reservation>> getReservationsByProjection(@PathVariable Long id){
		Projection projection = projectionService.findOne(id);
		if(projection == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		List<Reservation> reservations = reservationService.findByDateTimeProjection(projection);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reservations,HttpStatus.OK);
		
	}
	
	@GetMapping("/place/{id:\\d+}")
	public ResponseEntity<List<Reservation>> getReservationsByPlace(@PathVariable Long id){
		Place place = placeService.findOne(id);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		List<Reservation> reservations = reservationService.findByDateTimeProjectionPlace(place);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reservations,HttpStatus.OK);
		
	}
	
	@PostMapping
	public ResponseEntity<Reservation> add (@RequestBody @Valid Reservation input)
	{
		Reservation reservation= reservationService.add(input);
		return new ResponseEntity<>(reservation,HttpStatus.OK);
	}
}
