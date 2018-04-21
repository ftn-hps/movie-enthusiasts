package ftnhps.movieenthusiasts.places;

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

import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserType;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

	@Autowired
	private PlaceService placeService;
	@Autowired
	private PlaceConverter placeConverter;
	@Autowired
	private HttpSession session;
	
	@GetMapping
	public ResponseEntity<List<Place>> getPlaces() {
		List<Place> places = placeService.findAll();
		if(places == null || places.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(places, HttpStatus.OK);
	}
	
	@GetMapping("/{type:THEATER|CINEMA}")
	public ResponseEntity<List<Place>> getPlaces(@PathVariable PlaceType type) {
		List<Place> places = placeService.findAll(type);
		if(places == null || places.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(places, HttpStatus.OK);
	}
	
	@GetMapping("/{id:\\d+}")
	public ResponseEntity<Place> getPlace(@PathVariable Long id) {
		Place place = placeService.findOne(id);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(place, HttpStatus.OK);
	}
	
	@GetMapping("/chart/{id:\\d+}")
	public ResponseEntity<ChartDTO> getChartForPlace(@PathVariable Long id) {
		Place place = placeService.findOne(id);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		ChartDTO chartDTO = placeService.getChartData( place);
		
		return new ResponseEntity<>(chartDTO, HttpStatus.OK);
	}
	@GetMapping("/chart/month/{id:\\d+}")
	public ResponseEntity<ChartDTO> getChartForPlaceOneMonth(@PathVariable Long id) {
		Place place = placeService.findOne(id);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		ChartDTO chartDTO = placeService.getChartDataMonth( place);
		
		return new ResponseEntity<>(chartDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Place> add(@RequestBody PlaceDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.SYSADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Place place = placeConverter.fromDTO(input);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		place = placeService.add(place);
		return new ResponseEntity<>(place, HttpStatus.OK);
	}
	
	@PutMapping("/{id:\\d+}")
	public ResponseEntity<Place> edit(@PathVariable Long id, @RequestBody @Valid Place input) {
		Place place = placeService.edit(id, input);
		if(place == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(place, HttpStatus.OK);
	}
	
}
