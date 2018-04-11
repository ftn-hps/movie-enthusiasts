package ftnhps.movieenthusiasts.projections;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.places.Place;

@Transactional
@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired 
	private ProjectionRepository projectionRepository;
	
	@Override
	public Projection findOne(Long id) {
		return projectionRepository.findOne(id);
	}

	@Override
	public List<Projection> findAll() {
		return projectionRepository.findAll();
	}

	@Override
	public Projection add(Projection input) {
		return projectionRepository.save(input);
	}

	@Override
	public Projection edit(Long id, Projection input) {
		if(findOne(id) == null)
			return null;
		input.setId(id);
		return projectionRepository.save(input);
	}

	@Override
	public List<Projection> findProjectionsByPlace(Place place) {
		return projectionRepository.findByPlace(place);
	}
	

}
