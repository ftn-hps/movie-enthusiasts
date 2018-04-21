package ftnhps.movieenthusiasts.users.scale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserType;

@RestController
@RequestMapping("/api/scale")
public class ScaleController {
	
	@Autowired
	ScaleService scaleService;
	@Autowired
	ScaleConverter scaleConverter;
	@Autowired
	HttpSession session;
	
	@GetMapping
	public ResponseEntity<Scale> getScale() {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.SYSADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Scale scale = scaleService.getActive();
		if(scale == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(scale, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Scale> setScale(@RequestBody ScaleDTO input) {
		User user = (User) session.getAttribute("user");
		if(user == null || !user.getUserType().equals(UserType.SYSADMIN))
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Scale scale = scaleConverter.fromDTO(input);
		if(scale == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		scale = scaleService.setActive(scale);
		return new ResponseEntity<>(scale, HttpStatus.OK);
	}
}
