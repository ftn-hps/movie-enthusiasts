package ftnhps.movieenthusiasts.DateAndTime;

import java.util.List;

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

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.hall.HallService;
import ftnhps.movieenthusiasts.places.PlaceService;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionService;

@RestController
@RequestMapping("/api/dateAndTimeOfProjections")
public class DateAndTimeController {


	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private HallService hallService;
	@Autowired 
	private DateAndTimeService dateAndTimeOfProjectionService;
	
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
		DateAndTime dateAndTimeOfProjection = dateAndTimeOfProjectionService.add(input);
		return new ResponseEntity<DateAndTime>(dateAndTimeOfProjection,HttpStatus.OK);
	}
	
	@PutMapping("/{id://d+}")
	public ResponseEntity<DateAndTime> edit (@PathVariable Long id,@RequestBody @Valid DateAndTime input)
	{
		DateAndTime dateAndTimeOfProjection = dateAndTimeOfProjectionService.edit(id,input);
		if(dateAndTimeOfProjection == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<DateAndTime>(dateAndTimeOfProjection,HttpStatus.OK);
	}
	
	
}
