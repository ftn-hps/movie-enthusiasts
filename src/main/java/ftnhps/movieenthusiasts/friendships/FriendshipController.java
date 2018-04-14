package ftnhps.movieenthusiasts.friendships;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftnhps.movieenthusiasts.users.User;

@RestController
@RequestMapping("/api/friendships")
public class FriendshipController {

	@Autowired
	private FriendshipService friendshipService;
	@Autowired
	private HttpSession session;
	@Autowired
	private FriendshipConverter friendshipConverter;
	
	@GetMapping
	public ResponseEntity<List<FriendshipDTO>> getAll() {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		List<Friendship> friendships = friendshipService.findAllOfUser(user);
		if(friendships == null || friendships.isEmpty())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		List<FriendshipDTO> dto = friendshipConverter.toDTO(friendships, user);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PostMapping("/{receiverId:\\d+}")
	public ResponseEntity<FriendshipDTO> add(@PathVariable Long receiverId) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Friendship friendship = friendshipService.add(user.getId(), receiverId);
		if(friendship == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		
		FriendshipDTO dto = friendshipConverter.toDTO(friendship, user);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PutMapping("/{id:\\d+}")
	public ResponseEntity<FriendshipDTO> accept(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		Friendship friendship = friendshipService.accept(id, user);
		if(friendship == null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);

		FriendshipDTO dto = friendshipConverter.toDTO(friendship, user);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id:\\d+}")
	public ResponseEntity<?> remove(@PathVariable Long id) {
		User user = (User) session.getAttribute("user");
		if(user == null)
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		
		if(friendshipService.remove(id, user)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
}
