package ftnhps.movieenthusiasts.fanzone.propused;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftnhps.movieenthusiasts.fanzone.bid.Bid;
import ftnhps.movieenthusiasts.users.User;

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
	public List<PropUsed> findAll(Boolean approved, Bid bid) {
		List<PropUsed> props = propUsedRepository.findByApprovedAndAcceptedBid(approved, bid);
		List<PropUsed> ret = new ArrayList<>();
		for (PropUsed prop : props) {
			if(prop.getDate().isAfter(LocalDateTime.now(ZoneId.of("Z"))))
				ret.add(prop);
		}
		return ret;
	}
	
	@Override
	public List<List<PropUsed>> findAll(User user) {
		List<PropUsed> props = propUsedRepository.findByUser_Id(user.getId());
		if(props == null || props.isEmpty())
			return null;
		List<PropUsed> active = new ArrayList<>();
		List<PropUsed> inactive = new ArrayList<>();
		for (PropUsed prop : props) {
			if(prop.getDate().isBefore(LocalDateTime.now(ZoneId.of("Z"))) || prop.getAcceptedBid() != null || prop.getApproved() != true)
				inactive.add(prop);
			else 
				active.add(prop);
		}
		List<List<PropUsed>> ret = new ArrayList<>();
		ret.add(active);
		ret.add(inactive);
		return ret;
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
