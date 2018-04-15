package ftnhps.movieentusiasts.DateAndTimeOfProjection;

import java.util.List;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

public interface DateAndTimeOfProjectionService {

	DateAndTimeOfProjection findOne(Long id);
	
	List<DateAndTimeOfProjection> findAll();
	
	List<DateAndTimeOfProjection> findByProjectionAndHall(Projection projection,
			Hall hall);
	
	DateAndTimeOfProjection add(DateAndTimeOfProjection input);
	
	DateAndTimeOfProjection edit(Long id, DateAndTimeOfProjection input);
	
	
}
