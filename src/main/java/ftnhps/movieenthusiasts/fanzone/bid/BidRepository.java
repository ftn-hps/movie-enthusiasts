package ftnhps.movieenthusiasts.fanzone.bid;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
	
	List<Bid> findByPropIdOrderByIdDesc(Long propId);
}
