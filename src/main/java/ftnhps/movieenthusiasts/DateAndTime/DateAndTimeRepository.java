package ftnhps.movieenthusiasts.DateAndTime;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

@Repository
public interface DateAndTimeRepository extends JpaRepository<DateAndTime, Long>{

	List<DateAndTime> findByProjection(Projection projection);
	
	List<DateAndTime> findByProjectionAndTimeStampGreaterThan(Projection projection, Long timeStamp);
	
	List<DateAndTime> findByProjectionAndHall(Projection projection,Hall hall);
}
