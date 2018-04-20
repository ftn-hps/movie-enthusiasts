package ftnhps.movieenthusiasts.hall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.projections.Projection;

@Service
@Transactional(readOnly = true)
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
	@Transactional(readOnly = false)
	public Hall add(Hall input) {
		return hallRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Hall edit(Long id, Hall input) {
		if(findOne(id) == null)
			return null;
		input.setId(id);
		return hallRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public Hall delete(Long id) {
		Hall hall = hallRepository.findOne(id);
		if(hall == null)
			return null;
		try {
		hallRepository.delete(hall);
		}catch (Exception e) {
			System.out.println("Can't deleti hall which is already referenced");
			return null;
		}
		return hall;
	}
	
	
	
	

}
