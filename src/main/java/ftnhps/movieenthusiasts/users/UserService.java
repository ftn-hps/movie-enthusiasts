package ftnhps.movieenthusiasts.users;

import java.util.List;

public interface UserService {

	User findOne(Long id);

	List<User> findAll();

	User register(User user);

	User logIn(User user);

}
