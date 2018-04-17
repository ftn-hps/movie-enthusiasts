package ftnhps.movieenthusiasts.fanzone;

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
		return bidRepository.findByPropIdOrderByIdDesc(propId);
	}

	@Override
	public Bid add(Bid input) {
		return bidRepository.save(input);
	}

}
