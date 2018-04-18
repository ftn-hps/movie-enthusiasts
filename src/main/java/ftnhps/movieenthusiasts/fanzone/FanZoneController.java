package ftnhps.movieenthusiasts.fanzone;


import java.util.List;

import javax.servlet.http.HttpSession;
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

import ftnhps.movieenthusiasts.fanzone.bid.Bid;
import ftnhps.movieenthusiasts.fanzone.bid.BidConverter;
import ftnhps.movieenthusiasts.fanzone.bid.BidDTO;
import ftnhps.movieenthusiasts.fanzone.bid.BidService;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNew;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNewConverter;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNewDTO;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNewService;
import ftnhps.movieenthusiasts.fanzone.propused.PropUsed;
import ftnhps.movieenthusiasts.fanzone.propused.PropUsedConverter;
import ftnhps.movieenthusiasts.fanzone.propused.PropUsedDTO;
import ftnhps.movieenthusiasts.fanzone.propused.PropUsedService;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserType;

@RestController
@RequestMapping("/api/fanzone")
public class FanZoneController {
	
	@Autowired
	private PropNewService propNewService;
	@Autowired
	private PropNewConverter propNewConverter;
	@Autowired
	private PropUsedService propUsedService;
	@Autowired
	private PropUsedConverter propUsedConverter;
	@Autowired
	private BidService bidService;
	@Autowired
	private BidConverter bidConverter;
	@Autowired
	private HttpSession session;
	
	@GetMapping("/propsnew")
	public ResponseEntity<List<PropNew>> getPropsNew() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<PropNew> propsNew = propNewService.findAll();
		if(propsNew == null || propsNew.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsNew, HttpStatus.OK);
	}
	
	@GetMapping("/propsnew/place/{placeId:\\d+}")
	public ResponseEntity<List<PropNew>> getPropsNew(@PathVariable Long placeId) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<PropNew> propsNew = propNewService.findAll(placeId);
		if(propsNew == null || propsNew.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsNew, HttpStatus.OK);
	}
	
	@GetMapping("/propsnew/{id:\\d+}")
	public ResponseEntity<PropNew> getPropNew(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropNew propNew = propNewService.findOne(id);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propNew, HttpStatus.OK);
	}
	
	@PostMapping("/propsnew/add")
	public ResponseEntity<PropNew> add(@RequestBody PropNewDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.FANZONEADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropNew propNew = propNewConverter.fromDTO(input);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		propNew = propNewService.add(propNew);
		return new ResponseEntity<>(propNew, HttpStatus.OK);
	}
	
	@PutMapping("/propsnew/edit/{id:\\d+}")
	public ResponseEntity<PropNew> edit(@PathVariable Long id, @RequestBody PropNewDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.FANZONEADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropNew propNew = propNewConverter.fromDTO(input);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		propNew = propNewService.edit(id, propNew);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propNew, HttpStatus.OK);
	}
	
	@GetMapping("/propsused")
	public ResponseEntity<List<PropUsed>> getPropsUsed() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<PropUsed> propsUsed = propUsedService.findAll(true);
		if(propsUsed == null || propsUsed.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsUsed, HttpStatus.OK);
	}
	
	@GetMapping("/propsused/approved/{app:TRUE|FALSE}")
	public ResponseEntity<List<PropUsed>> getPropsUsed(@PathVariable String app) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.FANZONEADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
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
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropUsed propUsed = propUsedService.findOne(id);
		if(propUsed == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propUsed, HttpStatus.OK);
	}
	
	@PostMapping("/propsused/add")
	public ResponseEntity<PropUsed> add(@RequestBody PropUsedDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropUsed propUsed = propUsedConverter.fromDTO(input, user);
		if(propUsed == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		//propUsed.setApproved(true); //TODO promeniti u false kad se napravi admin fanzone
		propUsed = propUsedService.add(propUsed);
		return new ResponseEntity<>(propUsed, HttpStatus.OK);
	}
	
	@PutMapping("/propsused/{id:\\d+}/approve/{app:TRUE|FALSE}")
	public ResponseEntity<PropUsed> approvePropUsed(@PathVariable Long id, @PathVariable String app) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.FANZONEADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Boolean a = true;
		if(app.equals("FALSE"))
			a = false;
		
		PropUsed propUsed = propUsedService.findOne(id);
		if(propUsed == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(a) {
			propUsed.setApproved(a);
		} else {
			propUsed.setApproved(null);
			//TODO obavestiti o odbijanju oglasa
		}
		
		propUsed = propUsedService.edit(id, propUsed);
		return new ResponseEntity<>(propUsed, HttpStatus.OK);
	}
	
	@GetMapping("/propsused/bids/{propId:\\d+}")
	public ResponseEntity<List<Bid>> getBids(@PathVariable Long propId) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Bid> bids = bidService.findAll(propId);
		if(bids == null || bids.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(bids, HttpStatus.OK);
	}
	
	@PostMapping("/propsused/bids/add")
	public ResponseEntity<Bid> add(@RequestBody BidDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Bid bid = bidConverter.fromDTO(input, user);
		if(bid == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		bid = bidService.add(bid);
		return new ResponseEntity<>(bid, HttpStatus.OK);
	}
}
