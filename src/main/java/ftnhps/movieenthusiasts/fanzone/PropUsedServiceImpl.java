package ftnhps.movieenthusiasts.fanzone;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class PropUsedServiceImpl implements PropUsedService {
	
	@Autowired
	private PropUsedRepository propUsedRepository;

	@Override
	public PropUsed findOne(Long id) {
		return propUsedRepository.findOne(id);
	}

	@Override
	public List<PropUsed> findAll() {
		return propUsedRepository.findAll();
	}
	
	@Override
	public List<PropUsed> findAll(Boolean approved) {
		return propUsedRepository.findByApproved(approved);
	}

	@Override
	public PropUsed add(PropUsed input) {
		return propUsedRepository.save(input);
	}

	@Override
	public PropUsed edit(Long id, PropUsed input) {
		if(findOne(id) == null)
			return null;

		input.setId(id);
		return propUsedRepository.save(input);
	}

}
