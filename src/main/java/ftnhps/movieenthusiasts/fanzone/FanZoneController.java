package ftnhps.movieenthusiasts.fanzone;


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

@RestController
@RequestMapping("/api/fanzone")
public class FanZoneController {
	
	@Autowired
	private PropNewService propNewService;
	@Autowired
	private PropUsedService propUsedService;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/propsnew")
	public ResponseEntity<List<PropNew>> getPropsNew() {
		List<PropNew> propsNew = propNewService.findAll();
		if(propsNew == null || propsNew.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsNew, HttpStatus.OK);
	}
	
	@GetMapping("/propsnew/place/{placeId:\\d+}")
	public ResponseEntity<List<PropNew>> getPropsNew(@PathVariable Long placeId) {
		List<PropNew> propsNew = propNewService.findAll(placeId);
		if(propsNew == null || propsNew.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsNew, HttpStatus.OK);
	}
	
	@GetMapping("/propsnew/{id:\\d+}")
	public ResponseEntity<PropNew> getPropNew(@PathVariable Long id) {
		PropNew propNew = propNewService.findOne(id);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propNew, HttpStatus.OK);
	}
	
	@PostMapping("/propsnew/add")
	public ResponseEntity<PropNew> add(@RequestBody @Valid PropNew input) {
		PropNew propNew = propNewService.add(input);
		return new ResponseEntity<>(propNew, HttpStatus.OK);
	}
	
	@PutMapping("/propsnew/edit/{id:\\d+}")
	public ResponseEntity<PropNew> edit(@PathVariable Long id, @RequestBody @Valid PropNew input) {
		PropNew propNew = propNewService.edit(id, input);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propNew, HttpStatus.OK);
	}
	
	@GetMapping("/propsused")
	public ResponseEntity<List<PropUsed>> getPropsUsed() {
		List<PropUsed> propsUsed = propUsedService.findAll(true);
		if(propsUsed == null || propsUsed.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsUsed, HttpStatus.OK);
	}
	
	@GetMapping("/propsused/approved/{app:TRUE|FALSE}")
	public ResponseEntity<List<PropUsed>> getPropsUsed(@PathVariable String app) {
		Boolean a = true;
		if(app.equals("FALSE"))
			a = false;
		List<PropUsed> propsUsed = propUsedService.findAll(a);
		if(propsUsed == null || propsUsed.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsUsed, HttpStatus.OK);
	}
	
	@GetMapping("/propsused/{id:\\d+}")
	public ResponseEntity<PropUsed> getPropUsed(@PathVariable Long id) {
		PropUsed propUsed = propUsedService.findOne(id);
		if(propUsed == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propUsed, HttpStatus.OK);
	}
	
	@PostMapping("/propsused/add")
	public ResponseEntity<PropUsed> add(@RequestBody @Valid PropUsed input) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		input.setUserId(user.getId());
		input.setApproved(true); //TODO promeniti u false kad se napravi admin fanzone
		PropUsed propUsed = propUsedService.add(input);
		return new ResponseEntity<>(propUsed, HttpStatus.OK);
	}
	
	@PutMapping("/propsused/edit/{id:\\d+}")
	public ResponseEntity<PropUsed> edit(@PathVariable Long id, @RequestBody @Valid PropUsed input) {
		PropUsed propUsed = propUsedService.edit(id, input);
		if(propUsed == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propUsed, HttpStatus.OK);
	}
}
