package ftnhps.movieenthusiasts.projections;

import java.util.List;

import javax.servlet.http.HttpSession;
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
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserType;

@RestController
@RequestMapping("/api/projections")
public class ProjectionController {

	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public ResponseEntity<List<Projection>> getProjections(){
		List<Projection> projections = projectionService.findAll();
		if(projections == null || projections.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(projections,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Projection> getProjection(@PathVariable Long id){
		Projection projection = projectionService.findOne(id);
		if( projection == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(projection, HttpStatus.OK);
	}
	
	@GetMapping("/place/{id:\\d+}")
	public ResponseEntity<List<Projection>> getProjectionByPlaceId(@PathVariable Long id){
		Place place = placeService.findOne(id);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<Projection> projections = projectionService.findProjectionsByPlace(place);
		if(projections == null || projections.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(projections, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Projection> add (@RequestBody @Valid Projection input)
	{
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.PLACEADMIN)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		
		Projection projection = projectionService.add(input);
		if(projection == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(projection,HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<Projection> edit( @RequestBody @Valid Projection input){
		
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		if(user.getUserType() != UserType.PLACEADMIN)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Projection projection = projectionService.edit(input.getId(), input);
		if(projection == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(projection,HttpStatus.OK);
	}
	
	
	
	
	
}
