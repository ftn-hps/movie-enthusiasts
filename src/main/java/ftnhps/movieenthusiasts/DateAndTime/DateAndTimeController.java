package ftnhps.movieenthusiasts.DateAndTime;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.hall.HallService;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionService;
import ftnhps.movieenthusiasts.users.User;

@RestController
@RequestMapping("/api/dateAndTimeOfProjections")
public class DateAndTimeController {


	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private HallService hallService;
	@Autowired 
	private DateAndTimeService dateAndTimeOfProjectionService;
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public ResponseEntity<List<DateAndTime>> getDateAndTimeOfProjections(){
		List<DateAndTime> dateAndTimeOfProjections = dateAndTimeOfProjectionService.findAll();
		if(dateAndTimeOfProjections == null || dateAndTimeOfProjections.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(dateAndTimeOfProjections,HttpStatus.OK);
		
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<DateAndTime> getDateAndTimeOfProjection(@PathVariable Long id){
		DateAndTime dateAndTimeOfProjection = dateAndTimeOfProjectionService.findOne(id);
		if(dateAndTimeOfProjection == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(dateAndTimeOfProjection,HttpStatus.OK);
		
	}
	
	@GetMapping("/projectionAndHall/{idProjection:\\d+}/{idHall:\\\\d+}")
	public ResponseEntity<List<DateAndTime>> getDateAndTimeOfProjection(@PathVariable Long idProjection,
			@PathVariable Long idHall){
		Projection projection = projectionService.findOne(idProjection);
		Hall hall = hallService.findOne(idHall);
		
		if(projection == null || hall == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		List<DateAndTime> dateAndTimeOfProjection = dateAndTimeOfProjectionService.findByProjectionAndHall(projection, hall);
		if(dateAndTimeOfProjection == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(dateAndTimeOfProjection,HttpStatus.OK);
		
	}
	
	@GetMapping("/projection/{idProjection:\\d+}")
	public ResponseEntity<List<DateAndTime>> getDateAndTimeOfProjectionByProjId(@PathVariable Long idProjection){
		Projection projection = projectionService.findOne(idProjection);
		
		if(projection == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		List<DateAndTime> dateAndTimeOfProjection = dateAndTimeOfProjectionService.findByProjection(projection);
		if(dateAndTimeOfProjection == null )
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(dateAndTimeOfProjection,HttpStatus.OK);
		
	}
	
	
	@PostMapping
	public ResponseEntity<DateAndTime> add (@RequestBody @Valid DateAndTime input)
	{
		User user = (User) session.getAttribute("user");
		//TODO Check in user in admin of place
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		DateAndTime dateAndTimeOfProjection = dateAndTimeOfProjectionService.add(input);
		return new ResponseEntity<DateAndTime>(dateAndTimeOfProjection,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id:\\d+}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		//TODO Check in user in admin of place
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		if(dateAndTimeOfProjectionService.remove(id)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	
}
