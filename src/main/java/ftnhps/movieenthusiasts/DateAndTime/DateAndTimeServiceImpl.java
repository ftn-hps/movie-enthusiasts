package ftnhps.movieenthusiasts.DateAndTime;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.users.User;


@Transactional
@Service
public class DateAndTimeServiceImpl implements DateAndTimeService{

	@Autowired
	private DateAndTimeRepository dateAndTimeOfProjectionRepository;
	
	@Override
	public DateAndTime findOne(Long id) {
		return dateAndTimeOfProjectionRepository.findOne(id);
	}

	@Override
	public List<DateAndTime> findAll() {
		return dateAndTimeOfProjectionRepository.findAll();
	}

	@Override
	public List<DateAndTime> findByProjectionAndHall(Projection projection, Hall hall) {
		return dateAndTimeOfProjectionRepository.findByProjectionAndHall(projection, hall);
	}

	@Override
	public DateAndTime add(DateAndTime input) {
		return dateAndTimeOfProjectionRepository.save(input);
	}

	@Override
	public DateAndTime edit(Long id, DateAndTime input) {
		if(findOne(id) == null)
			return null;

		input.setId(id);
		return dateAndTimeOfProjectionRepository.save(input);
	}

	@Override
	public List<DateAndTime> findByProjection(Projection projection) {
		return dateAndTimeOfProjectionRepository.findByProjection(projection);
	}

	@Override
	public boolean remove(Long id) {
		DateAndTime dateAndTime = dateAndTimeOfProjectionRepository.findOne(id);
		if(dateAndTime == null)
			return false;
		dateAndTimeOfProjectionRepository.delete(dateAndTime);
		return true;
		
	}

	
}
