package ftnhps.movieenthusiasts.projections;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnhps.movieenthusiasts.places.Place;



@Repository
public interface ProjectionRepository extends JpaRepository<Projection, Long>{

	List<Projection> findByPlace(Place place);
}
