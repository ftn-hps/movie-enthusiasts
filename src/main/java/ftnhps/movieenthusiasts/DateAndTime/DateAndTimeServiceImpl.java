package ftnhps.movieenthusiasts.DateAndTime;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.hall.HallRepository;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionRepository;



@Service
@Transactional(readOnly = true)
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
	public DateAndTime findFutureOne(Long id) {
		return dateAndTimeOfProjectionRepository
				.findByIdAndTimeStampGreaterThan(id, System.currentTimeMillis()/1000);
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
	@Transactional(readOnly = false)
	public DateAndTime add(DateAndTime input) {
		Hall hall = input.getHall();
		input.setHall( hallRepository.findOne(hall.getId()) );
		if(input.getHall() == null)
			return null;
		
		Projection projection = input.getProjection();
		input.setProjection(projectionRepository.findOne(projection.getId()));
		if(input.getProjection() == null)
			return null;
		
		//postaviti layout dateTime na onaj koji sala ima sama po sebi
		input.setReservationLayout(input.getHall().getLayout());
		
		return dateAndTimeOfProjectionRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
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
	public List<DateAndTime> findFutureByProjection(Projection projection) {
		return dateAndTimeOfProjectionRepository
				.findByProjectionAndTimeStampGreaterThan(projection, System.currentTimeMillis()/1000);
	}
	
	@Override
	@Transactional(readOnly = false)
	public boolean remove(Long id) {
		DateAndTime dateAndTime = dateAndTimeOfProjectionRepository.findOne(id);
		if(dateAndTime == null)
			return false;
		try {
		dateAndTimeOfProjectionRepository.delete(dateAndTime);
		}catch (Exception e) {
			System.out.println("DateAndTime can't be deleted if used");
			return false;
		}
		return true;
		
	}


	
}
