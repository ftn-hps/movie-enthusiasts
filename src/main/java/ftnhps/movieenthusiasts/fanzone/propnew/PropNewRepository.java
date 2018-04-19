package ftnhps.movieenthusiasts.fanzone.propnew;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropNewRepository extends JpaRepository<PropNew, Long> {
	
	PropNew findOneByIdAndDeleted(Long id, Boolean deleted);
	
	List<PropNew> findByDeleted(Boolean deleted);
	
	List<PropNew> findByPlace_IdAndDeleted(Long placeId, Boolean deleted);
	
}
