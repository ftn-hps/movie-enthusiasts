package ftnhps.movieenthusiasts.fanzone.propnew;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropNewRepository extends JpaRepository<PropNew, Long> {
	
	List<PropNew> findByPlace_Id(Long placeId);
	
}
