package ftnhps.movieenthusiasts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.reservations.Reservation;
import ftnhps.movieenthusiasts.reservations.ReservationService;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserService;

@Component
public class CronJobs {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ReservationService reservationService;
	
	@Scheduled(cron = "${cron.rewards}")
	public void rewardUsers() {
		System.out.println("CRON JOB started: rewards");
		List<Reservation> reservations = reservationService.findHistory();
		for(Reservation reservation : reservations) {
			// Skip fast reservations
			if(reservation.getUser() == null)
				continue;
			if(!reservation.isRewarded()) {
				reservation.setRewarded(true);
				User user = userService.rewardUser(reservation.getUser().getId(), 5);
				userService.edit(user.getId(), user);
				reservationService.edit(reservation.getId(), reservation);
			}
		}
	}
	
}
