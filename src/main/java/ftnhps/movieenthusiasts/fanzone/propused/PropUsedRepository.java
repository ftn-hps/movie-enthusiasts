package ftnhps.movieenthusiasts.fanzone.propused;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropUsedRepository extends JpaRepository<PropUsed, Long> {
	
	List<PropUsed> findByApproved(Boolean approved);
	
}
