package ftnhps.movieenthusiasts.fanzone.propused;

import java.util.List;

import ftnhps.movieenthusiasts.fanzone.bid.Bid;
import ftnhps.movieenthusiasts.users.User;

public interface PropUsedService {
	
	PropUsed findOne(Long id);
	
	List<PropUsed> findAll();
	
	List<PropUsed> findAll(Boolean approved, Bid bid);
	
	List<List<PropUsed>> findAll(User user);
	
	PropUsed add(PropUsed input);
	
	PropUsed edit(Long id, PropUsed input);

}
