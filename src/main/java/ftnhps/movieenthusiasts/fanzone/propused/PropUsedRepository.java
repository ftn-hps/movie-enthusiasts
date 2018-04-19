package ftnhps.movieenthusiasts.fanzone.propused;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftnhps.movieenthusiasts.fanzone.bid.Bid;

public interface PropUsedRepository extends JpaRepository<PropUsed, Long> {
	
	List<PropUsed> findByApprovedAndAcceptedBid(Boolean approved, Bid bid);
	
	List<PropUsed> findByUser_Id(Long userId);
	
}
