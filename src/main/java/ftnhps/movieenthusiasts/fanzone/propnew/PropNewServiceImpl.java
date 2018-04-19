package ftnhps.movieenthusiasts.fanzone.propnew;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PropNewServiceImpl implements PropNewService {
	
	@Autowired
	private PropNewRepository propNewRepository;

	@Override
	public PropNew findOne(Long id) {
		return propNewRepository.findOneByIdAndDeleted(id, false);
	}

	@Override
	public List<PropNew> findAll() {
		return propNewRepository.findByDeleted(false);
	}

	@Override
	public List<PropNew> findAll(Long placeId) {
		return propNewRepository.findByPlace_IdAndDeleted(placeId, false);
	}

	@Override
	public PropNew add(PropNew input) {
		return propNewRepository.save(input);
	}

	@Override
	public PropNew edit(Long id, PropNew input) {
		if(propNewRepository.findOne(id) == null)
			return null;

		input.setId(id);
		return propNewRepository.save(input);
	}

}
