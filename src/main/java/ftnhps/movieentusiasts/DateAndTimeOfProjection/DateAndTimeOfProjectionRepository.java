package ftnhps.movieentusiasts.DateAndTimeOfProjection;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

@Repository
public interface DateAndTimeOfProjectionRepository extends JpaRepository<DateAndTimeOfProjection, Long>{

	List<DateAndTimeOfProjection> findByProjection(Projection projection);
	List<DateAndTimeOfProjection> findByProjectionAndHall(Projection projection,Hall hall);
}
