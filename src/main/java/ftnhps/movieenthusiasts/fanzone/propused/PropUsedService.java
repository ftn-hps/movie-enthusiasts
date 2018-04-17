package ftnhps.movieenthusiasts.fanzone.propused;

import java.util.List;

public interface PropUsedService {
	
	PropUsed findOne(Long id);
	
	List<PropUsed> findAll();
	
	List<PropUsed> findAll(Boolean approved);
	
	PropUsed add(PropUsed input);
	
	PropUsed edit(Long id, PropUsed input);

}
