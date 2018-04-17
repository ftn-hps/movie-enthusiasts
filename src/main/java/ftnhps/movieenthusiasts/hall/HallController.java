package ftnhps.movieenthusiasts.hall;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserType;

@RestController
@RequestMapping("/api/halls")
public class HallController {
	
	@Autowired 
	private HallService hallService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public ResponseEntity<List<Hall>> getHalls()
	{
		List<Hall> halls= hallService.findAll();
		if(halls == null || halls.isEmpty() )
			return new ResponseEntity<List<Hall>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<Hall>>(halls,HttpStatus.OK);
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Hall> getHall(@PathVariable Long id)
	{
		Hall hall= hallService.findOne(id);
		if(hall == null )
			return new ResponseEntity<Hall>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Hall>(hall,HttpStatus.OK);
	}
	
	@GetMapping("/place/{id:\\d+}")
	public ResponseEntity<List<Hall>> getHallsByPlaceId(@PathVariable Long id){
		Place place = placeService.findOne(id);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<Hall> halls = hallService.findByPalce(place);
		if(halls == null || halls.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(halls, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Hall> add (@RequestBody @Valid Hall input)
	{
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.PLACEADMIN)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Hall hall = hallService.add(input);
		return new ResponseEntity<Hall>(hall,HttpStatus.OK);
	}
	
	@PutMapping("/{id://d+}")
	public ResponseEntity<Hall> edit(@PathVariable Long id, @RequestBody @Valid Hall input){
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.PLACEADMIN)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Hall hall = hallService.edit(id, input);
		return new ResponseEntity<Hall>(hall,HttpStatus.OK);
	}
}
