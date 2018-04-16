package ftnhps.movieenthusiasts.DateAndTime;

import java.util.List;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

public interface DateAndTimeService {

	DateAndTime findOne(Long id);
	
	List<DateAndTime> findAll();
	
	List<DateAndTime> findByProjection(Projection projection);
	
	List<DateAndTime> findByProjectionAndHall(Projection projection,
			Hall hall);
	
	DateAndTime add(DateAndTime input);
	
	DateAndTime edit(Long id, DateAndTime input);
	
	
}
