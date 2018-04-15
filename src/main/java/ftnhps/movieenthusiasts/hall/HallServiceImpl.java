package ftnhps.movieenthusiasts.hall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.places.Place;

@Service
public class HallServiceImpl implements HallService{
	
	@Autowired
	private HallRepository hallRepository;

	@Override
	public Hall findOne(Long id) {
		return hallRepository.findOne(id);
	}

	@Override
	public List<Hall> findAll() {
		return hallRepository.findAll();
	}

	@Override
	public List<Hall> findByPalce(Place place) {
		return hallRepository.findByPlace(place);
	}

	@Override
	public Hall add(Hall input) {
		return hallRepository.save(input);
	}

	@Override
	public Hall edit(Long id, Hall input) {
		if(findOne(id) == null)
			return null;
		input.setId(id);
		return hallRepository.save(input);
	}
	
	
	
	

}
