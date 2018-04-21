package ftnhps.movieenthusiasts.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Value("${threshold.silver}")
	private int thresholdSilver;
	@Value("${threshold.gold}")
	private int thresholdGold;

	@Override
	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false)
	public User register(User user) {
		User existing = userRepository.findByEmail(user.getEmail());
		if(existing == null)
			return userRepository.save(user);
		
		return null;
	}
	
	@Override
	@Transactional(readOnly = false)
	public User confirm(String token) {
		User existing = userRepository.findByToken(token);
		if(existing == null)
			return null;
		
		existing.setEmailConfirmed(true);
		return userRepository.save(existing);
	}

	@Override
	public User logIn(User user) {
		User existing = userRepository.findByEmail(user.getEmail());
		if(existing == null)
			return null;
		
		if(existing.getPassword().equals(user.getPassword())
				&& existing.isEmailConfirmed())
			return existing;
		
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public User edit(Long id, User user) {
		User existing = findOne(id);
		if(existing == null)
			return null;

		user.setId(id);
		user.setEmail(existing.getEmail());
		user.setUserType(existing.getUserType());
		
		if(user.getPassword() == null || user.getPassword().equals(existing.getPassword())) {
			user.setPassword(existing.getPassword());
			user.setPasswordChanged(existing.getPasswordChanged());
		} else if (!existing.getUserType().equals(UserType.VISITOR)) {
			user.setPasswordChanged(true);
		}
		
		return userRepository.save(user);
	}
	
	@Override
	@Transactional(readOnly = false)
	public User rewardUser(Long id, int increment) {
		User user = findOne(id);
		
		int oldPoints = user.getPoints();
		user.incrementPoints(increment);
		
		if(oldPoints < thresholdSilver && user.getPoints() >= thresholdSilver)
			user.setMedal(MedalType.SILVER);
		if(oldPoints < thresholdGold && user.getPoints() >= thresholdGold)
			user.setMedal(MedalType.GOLD);
		
		return user;
	}

}
