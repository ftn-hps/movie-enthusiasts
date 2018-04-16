package ftnhps.movieenthusiasts.fanzone;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropNewRepository extends JpaRepository<PropNew, Long> {
	
	List<PropNew> findByPlaceId(Long placeId);
	
}
