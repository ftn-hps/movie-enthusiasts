package ftnhps.movieenthusiasts.fanzone.bid;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class BidServiceImpl implements BidService {
	
	@Autowired
	private BidRepository bidRepository;

	@Override
	public Bid findOne(Long id) {
		return bidRepository.findOne(id);
	}

	@Override
	public List<Bid> findAll() {
		return bidRepository.findAll();
	}

	@Override
	public List<Bid> findAll(Long propId) {
		return bidRepository.findByPropUsed_IdOrderByIdDesc(propId);
	}

	@Override
	public Bid add(Bid input) {
		return bidRepository.save(input);
	}

	@Override
	public Bid edit(Long id, Bid input) {
		if(findOne(id) == null)
			return null;

		input.setId(id);
		return bidRepository.save(input);
	}

	@Override
	public Bid findAccepted(Long propId) {
		return bidRepository.findOneByPropUsed_Id(propId);
	}
	
}
