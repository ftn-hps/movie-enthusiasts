package ftnhps.movieenthusiasts.users;

import java.util.List;

public interface UserService {

	User findOne(Long id);

	List<User> findAll();

	User register(User user);

	User logIn(User user);

	User edit(Long id, User user);
	
	User rewardUser(Long id, int increment);

}
