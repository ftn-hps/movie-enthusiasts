package ftnhps.movieenthusiasts.fanzone.propnew;

import java.util.List;

public interface PropNewService {
	
	PropNew findOne(Long id);
	
	List<PropNew> findAll();
	
	List<PropNew> findAll(Long placeId);
	
	PropNew add(PropNew input);
	
	PropNew edit(Long id, PropNew input);

}
