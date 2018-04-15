package ftnhps.movieentusiasts.DateAndTimeOfProjection;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

@Service
@Transactional
public class DateAndTimeOfProjectionServiceImpl implements DateAndTimeOfProjectionService{

	@Autowired
	private DateAndTimeOfProjectionRepository dateAndTimeOfProjectionRepository;
	
	@Override
	public DateAndTimeOfProjection findOne(Long id) {
		return dateAndTimeOfProjectionRepository.findOne(id);
	}

	@Override
	public List<DateAndTimeOfProjection> findAll() {
		return dateAndTimeOfProjectionRepository.findAll();
	}

	@Override
	public List<DateAndTimeOfProjection> findByProjectionAndHall(Projection projection, Hall hall) {
		return dateAndTimeOfProjectionRepository.findByProjectionAndHall(projection, hall);
	}

	@Override
	public DateAndTimeOfProjection add(DateAndTimeOfProjection input) {
		return dateAndTimeOfProjectionRepository.save(input);
	}

	@Override
	public DateAndTimeOfProjection edit(Long id, DateAndTimeOfProjection input) {
		if(findOne(id) == null)
			return null;

		input.setId(id);
		return dateAndTimeOfProjectionRepository.save(input);
	}

	
}
