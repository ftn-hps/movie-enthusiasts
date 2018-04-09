package ftnhps.movieenthusiasts.places;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private PlaceRepository placeRepository;
	
	@Override
	public Place findOne(Long id) {
		return placeRepository.findOne(id);
	}

	@Override
	public List<Place> findAll() {
		return placeRepository.findAll();
	}

	@Override
	public List<Place> findAll(PlaceType type) {
		return placeRepository.findByType(type);
	}

}
