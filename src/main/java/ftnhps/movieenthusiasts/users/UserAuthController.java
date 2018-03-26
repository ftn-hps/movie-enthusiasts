package ftnhps.movieenthusiasts.users;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-auth")
public class UserAuthController {

	@Autowired
	private UserService userService;
	@Autowired
	private HttpSession session;

	@PostMapping
	public ResponseEntity<User> register(@RequestBody @Valid User user) {
		User registered = userService.register(user);
		if(registered == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		return new ResponseEntity<>(registered, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<User> logIn(@RequestBody @Valid User user) {
		User newUser = userService.logIn(user);
		if(newUser == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		session.setAttribute("user", newUser);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@DeleteMapping
	public ResponseEntity<User> logOut() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		session.invalidate();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<User> getUser() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}