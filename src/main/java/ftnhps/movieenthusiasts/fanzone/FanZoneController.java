package ftnhps.movieenthusiasts.fanzone;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ftnhps.movieenthusiasts.EmailUtils;
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
import ftnhps.movieenthusiasts.fanzone.reservation.PropReservation;
import ftnhps.movieenthusiasts.fanzone.reservation.PropReservationConverter;
import ftnhps.movieenthusiasts.fanzone.reservation.PropReservationDTO;
import ftnhps.movieenthusiasts.fanzone.reservation.PropReservationService;
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
	private PropReservationService propReservationService;
	@Autowired
	private PropReservationConverter propReservationConverter;
	@Autowired
	private PropUsedService propUsedService;
	@Autowired
	private PropUsedConverter propUsedConverter;
	@Autowired
	private BidService bidService;
	@Autowired
	private BidConverter bidConverter;
	@Autowired 
	private EmailUtils email;
	@Autowired
	private HttpSession session;
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private String getNewFileName(String oldName) {
		String[] split = oldName.split("\\.");
		String ext = "." + split[split.length - 1];
		return UUID.randomUUID().toString() + ext; 
	}
	
    @RequestMapping(value = "/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("propId") Long propId, @PathParam("propType") String propType) throws IOException {
    	User user = (User) session.getAttribute("user");
		if( user == null || (propType.equals("NEW") && !user.getUserType().equals(UserType.FANZONEADMIN)))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Object prop;
		
		if(propType.equals("NEW"))
			prop = propNewService.findOne(propId);
		else
			prop = propUsedService.findOne(propId);
		
		if(prop == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(propType.equals("USED") && ((PropUsed)prop).getUser().getId() != user.getId())
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		byte[] bytes;
        Path path;
        String fileName;
        
        if (!file.isEmpty()) {
        	try (InputStream input = file.getInputStream()) {
        	    try {
        	        ImageIO.read(input).toString();
        	        // It's an image (only BMP, GIF, JPG and PNG are recognized).
        	    } catch (Exception e) {
        	        return new ResponseEntity<>(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
        	    }
        	}
            bytes = file.getBytes();
            fileName = getNewFileName(file.getOriginalFilename());
            path = Paths.get("src","main","resources","static","img",fileName);
            Files.write(path, bytes);
        } else {
        	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        if(propType.equals("NEW")) {
        	PropNew propN = (PropNew) prop;
        	propN.setImagePath("/img/" + fileName);
        	propNewService.edit(propN.getId(), propN);
        } else {
        	PropUsed propU = (PropUsed) prop;
        	propU.setImagePath("/img/" + fileName);
        	propUsedService.edit(propU.getId(), propU);
        }
        
        System.out.println(String.format("receive %s from %s", file.getOriginalFilename(), propId));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
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
	
	@DeleteMapping("/propsnew/delete/{id:\\d+}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.FANZONEADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropNew propNew = propNewService.findOne(id);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		propNew.setDeleted(true);
		propNew = propNewService.edit(id, propNew);
		if(propNew == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/propsused")
	public ResponseEntity<List<PropUsed>> getPropsUsed() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<PropUsed> propsUsed = propUsedService.findAll(true, null);
		if(propsUsed == null || propsUsed.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(propsUsed, HttpStatus.OK);
	}
	
	@GetMapping("/propsused/user")
	public ResponseEntity<List<List<PropUsed>>> getUsersPropsUsed() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<List<PropUsed>> propsUsed = propUsedService.findAll(user);
		if(propsUsed == null)
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
		
		List<PropUsed> propsUsed = propUsedService.findAll(a, null);
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
		
		if( (propUsed.getApproved() == null || !propUsed.getApproved()) && (!user.getUserType().equals(UserType.FANZONEADMIN) && user.getId() != propUsed.getUser().getId()) )
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		if( (propUsed.getAcceptedBid() != null || propUsed.getDate().isBefore(LocalDateTime.now(ZoneId.of("Z")))) &&  (!user.getUserType().equals(UserType.FANZONEADMIN) && user.getId() != propUsed.getUser().getId()) )
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
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
	public ResponseEntity<PropUsed> approvePropUsed(@PathVariable Long id, @PathVariable String app) throws MailException, InterruptedException {
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
			email.sendEmail(propUsed.getUser(), "Prop ad declined", "We are sorry to inform you that your ad for prop " + propUsed.getName() + " was decilined.");
		}
		
		propUsed = propUsedService.edit(id, propUsed);
		return new ResponseEntity<>(propUsed, HttpStatus.OK);
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/propsused/bids/{propId:\\d+}")
	public ResponseEntity<List<Bid>> getBids(@PathVariable Long propId) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropUsed propUsed = propUsedService.findOne(propId);
		if(propUsed == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if( (propUsed.getApproved() == null || !propUsed.getApproved()) && (!user.getUserType().equals(UserType.FANZONEADMIN) && user.getId() != propUsed.getUser().getId()) )
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		if( (propUsed.getAcceptedBid() != null || propUsed.getDate().isBefore(LocalDateTime.now(ZoneId.of("Z")))) &&  (!user.getUserType().equals(UserType.FANZONEADMIN) && user.getId() != propUsed.getUser().getId()) )
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Bid> bids = bidService.findAll(propId);
		if(bids == null || bids.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(bids, HttpStatus.OK);
	}
	
	@PutMapping("/propsused/bids/accept/{bidId:\\d+}")
	public ResponseEntity<PropUsed> acceptBid(@PathVariable Long bidId) throws MailException, InterruptedException {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Bid bid = bidService.findOne(bidId);
		if(bid == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		PropUsed prop = bid.getPropUsed();
		if( prop.getAcceptedBid() != null || prop.getUser().getId() != user.getId() || prop.getDate().isBefore(LocalDateTime.now(ZoneId.of("Z"))) )
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		prop.setAcceptedBid(bid);
		prop = propUsedService.edit(prop.getId(), prop);
		sendBidNotifications(prop);
		return new ResponseEntity<>(prop, HttpStatus.OK);
	}
	
	private void sendBidNotifications(PropUsed prop) throws MailException, InterruptedException {
		List<Bid> bids = bidService.findAll(prop.getId());
		Bid acceptedBid = prop.getAcceptedBid();
		HashMap<Long, User> users = new HashMap<>(); 
		
		for (Bid bid : bids) {
			User user = bid.getUser();
			if(user.getId() != acceptedBid.getUser().getId()) {
				if(!users.containsKey(user.getId()))
					users.put(user.getId(), user);
			}
		}
		
		for (User u : users.values()) {
			email.sendEmail(u, "Bid not accepted", "Non of your bids on " + prop.getName() + " was accepted." );
		}
		
		email.sendEmail(acceptedBid.getUser(), "Bid accpeted", "Your bid of " + acceptedBid.getBid() + " on " + prop.getName() + " was accepted.");
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
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/propsnew/reservations")
	public ResponseEntity<List<PropReservation>> getReservations() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<PropReservation> reservations = propReservationService.findAll(user.getId(), false);
		if(reservations == null || reservations.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(reservations, HttpStatus.OK);
	}
	
	@PostMapping("/propsnew/reservations/add")
	public ResponseEntity<PropReservation> add(@RequestBody PropReservationDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropReservation reservation = propReservationConverter.fromDTO(input, user);
		if(reservation == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		reservation = propReservationService.add(reservation);
		return new ResponseEntity<>(reservation, HttpStatus.OK);
	}
	
	@DeleteMapping("/propsnew/reservations/delete/{resId:\\d+}")
	public ResponseEntity<PropReservation> cancelPropRes(@PathVariable Long resId) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		PropReservation reservation = propReservationService.findOne(resId);
		if(reservation == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(reservation.getUser().getId() != user.getId())
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		reservation.setCanceled(true);
		reservation = propReservationService.edit(resId, reservation);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
