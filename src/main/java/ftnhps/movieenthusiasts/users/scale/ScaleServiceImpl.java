package ftnhps.movieenthusiasts.users.scale;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.users.MedalType;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserRepository;

@Transactional(readOnly = true)
@Service
public class ScaleServiceImpl implements ScaleService {

	@Autowired
	ScaleRepository scaleRepository;
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Scale getActive() {
		List<Scale> scales = scaleRepository.findByActive(true);
		if(scales == null || scales.isEmpty() || scales.size() > 1)
			return null;
		return scales.get(0);
	}

	@Override
	@Transactional(readOnly = false)
	public Scale setActive(Scale input) {
		List<Scale> scales = scaleRepository.findByActive(true);
		if(scales != null) {
			for (Scale scale : scales) {
				scale.setActive(false);
				scaleRepository.save(scale);
			}
		}
		Scale scale =  scaleRepository.save(input);
		if(scale != null) {
			List<User> users = userRepository.findAll();
			if(users != null && !users.isEmpty()) {
				for (User user : users) {
					if(user.getPoints() < scale.getSilver())
						user.setMedal(MedalType.BRONZE);
					if(user.getPoints() >= scale.getSilver() && user.getPoints() < scale.getGold())
						user.setMedal(MedalType.SILVER);
					if(user.getPoints() >= scale.getGold())
						user.setMedal(MedalType.GOLD);
					
					userRepository.save(user);
				}
			}
		}
		return scale;
	}
}
