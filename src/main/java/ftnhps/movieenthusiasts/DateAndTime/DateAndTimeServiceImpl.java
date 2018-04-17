package ftnhps.movieenthusiasts.DateAndTime;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.hall.HallRepository;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionRepository;


@Transactional
@Service
public class DateAndTimeServiceImpl implements DateAndTimeService{

	@Autowired
	private DateAndTimeRepository dateAndTimeOfProjectionRepository;
	
	@Autowired
	private HallRepository hallRepository;
	
	@Autowired
	private ProjectionRepository projectionRepository;
	
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
		Hall hall = input.getHall();
		input.setHall( hallRepository.findOne(hall.getId()) );
		if(input.getHall() == null)
			return null;
		
		Projection projection = input.getProjection();
		input.setProjection(projectionRepository.findOne(projection.getId()));
		if(input.getProjection() == null)
			return null;
		
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
