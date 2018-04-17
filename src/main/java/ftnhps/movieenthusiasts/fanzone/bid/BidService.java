package ftnhps.movieenthusiasts.fanzone.bid;

import java.util.List;

public interface BidService {
	
	Bid findOne(Long id);
	
	List<Bid> findAll();
	
	List<Bid> findAll(Long propId);
	
	Bid add(Bid input);

}
