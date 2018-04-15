package ftnhps.movieenthusiasts.hall;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ftnhps.movieenthusiasts.places.Place;

@Repository
public interface HallRepository extends JpaRepository<Hall, Long>{

	List<Hall> findByPlace(Place place);
}
