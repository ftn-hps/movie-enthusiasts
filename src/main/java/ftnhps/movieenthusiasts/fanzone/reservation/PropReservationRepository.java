package ftnhps.movieenthusiasts.fanzone.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropReservationRepository extends JpaRepository<PropReservation, Long> {
	
	List<PropReservation> findByUser_IdAndCanceled(Long userId, Boolean canceled);

}
