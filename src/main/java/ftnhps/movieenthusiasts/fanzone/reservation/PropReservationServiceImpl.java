package ftnhps.movieenthusiasts.fanzone.reservation;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional(readOnly = true)
@Service
public class PropReservationServiceImpl implements PropReservationService {
	
	@Autowired
	PropReservationRepository propReservationRepository;
	
	@Override
	public PropReservation findOne(Long id) {
		return propReservationRepository.findOne(id);
	}

	@Override
	public List<PropReservation> findAll(Long userId, Boolean canceled) {
		return propReservationRepository.findByUser_IdAndCanceled(userId, canceled);
	}

	@Override
	@Transactional(readOnly = false)
	public PropReservation add(PropReservation input) {
		return propReservationRepository.save(input);
	}

	@Override
	@Transactional(readOnly = false)
	public PropReservation edit(Long id, PropReservation input) {
		if(findOne(id) == null)
			return null;
		
		input.setId(id);
		return propReservationRepository.save(input);
	}

}
