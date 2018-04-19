package ftnhps.movieenthusiasts;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.DateAndTime.DateAndTimeService;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNew;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNewService;
import ftnhps.movieenthusiasts.fanzone.propused.PropUsed;
import ftnhps.movieenthusiasts.fanzone.propused.PropUsedService;
import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.hall.HallService;
import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;
import ftnhps.movieenthusiasts.places.PlaceType;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionService;
import ftnhps.movieenthusiasts.reservations.Reservation;
import ftnhps.movieenthusiasts.reservations.ReservationService;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserService;
import ftnhps.movieenthusiasts.users.UserType;

@Component
public class TestData {

	@Autowired
	private UserService userService;
	@Autowired
	private PlaceService placeService;
	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private HallService hallService;
	@Autowired
	private DateAndTimeService dateAndTimeOfProjectionService;
	@Autowired
	private PropNewService propNewService;
	@Autowired
	private PropUsedService propUsedService;
	@Autowired
	private ReservationService reservationService;
	
	
	@PostConstruct
	private void init() {
		User user1 = new User("ddd@ddd.com", "dddddd", "Ddd", "Ddd", "Ddd", null);
		user1.setUserType(UserType.VISITOR);
		userService.register(user1);
		
		User user2 = new User("placeAdmin@aaa.com", "aaaaaa", "Aaa", "Aaa", "Aaa", null);
		user2.setUserType(UserType.PLACEADMIN);
		userService.register(user2);
		
		Place place1 = new Place(PlaceType.CINEMA,
				"Arena Cineplex",
				4,
				"Novi Sad",
				"Arena Cineplex je kompletno renovirana 2010. godine u skladu sa najnovijim svetskim standardima.");
		placeService.add(place1);
		
		Place place2 = new Place(PlaceType.THEATER,
				"Srpsko narodno pozoriste",
				5,
				"Novi Sad",
				"Srpsko narodno pozoriste je najstariji profesionalni teatar u Srbiji. "
				+ "Osnovano je 1861. godine u Novom Sadu i od tada neprestano funkcionise.");
		placeService.add(place2);
		
		Place place3 = new Place(PlaceType.THEATER,
				"Pozoriste mladih",
				3,
				"Novi Sad",
				"Pozoriste mladih osnovano je 1932. godine kao Lutkarsko pozoriste, pri Sokolskom drustvu u Novom Sadu.");
		placeService.add(place3);
		
		Projection projection1 = new Projection(place1,
				"R&J",
				"{artists:[\"Paja\" , \"Jare\"]}",
				"Drama", 
				"Paja",
				120, 
				"/img/placeholder.png",
				3, 
				"Klasicni romeo i julija",
				333.00);
		projectionService.add(projection1);
		
		Projection projection2 = new Projection(place1,
				"aaa",
				"{artists:[\"aa\" , \"Aa\"]}",
				"Aaaaa", 
				"Aaaaa",
				120, 
				"/img/placeholder.png",
				5, 
				"Aaaaaaa",
				333.00);
		projectionService.add(projection2);
		
		Projection projection3 = new Projection(place2,
				"Bbbb",
				"{artists:[\"bbbb\" , \"Bbbbb\"]}",
				"Bbbbbbb", 
				"Bbbbbb",
				120, 
				"/img/placeholder.png",
				5, 
				"Bbbbbbbbb",
				5000.00);
		projectionService.add(projection3);
		
		Hall hall1 = new Hall("sala1",3,4,"oooooooooooo",place1);
		hallService.add(hall1);
		
		Hall hall2 = new Hall("sala2",3,3,"ooooooooo",place1);
		hallService.add(hall2);
		
		Hall hall3 = new Hall("sala3",3,3,"xxoooooxx",place2);
		hallService.add(hall3);
		
		DateAndTime dateAndTime1 = new DateAndTime(
				new Long(1520352000),
				"oooooooooooo",
				projection1,
				hall1);
		dateAndTimeOfProjectionService.add(dateAndTime1);
		
		DateAndTime dateAndTime2 = new DateAndTime(
				new Long(1517569200),
				"xxoooooxx",
				projection3,
				hall3);
		dateAndTimeOfProjectionService.add(dateAndTime2);
		
		DateAndTime dateAndTime3 = new DateAndTime(
				new Long(1533722400),
				"xxoooooxx",
				projection2,
				hall2);
		dateAndTimeOfProjectionService.add(dateAndTime3);
		
		DateAndTime dateAndTime4 = new DateAndTime(
				new Long(1514800800),
				"xxoooooxx",
				projection2,
				hall2);
		dateAndTimeOfProjectionService.add(dateAndTime4);
		
		Reservation reservation1 = new Reservation(5.0, dateAndTime1, 3, user1);
		reservationService.add(reservation1);
		Reservation reservation2 = new Reservation(0.0, dateAndTime2, 4, user1);
		reservationService.add(reservation2);
		Reservation reservation3 = new Reservation(0.0, dateAndTime3, 4, user1);
		reservationService.add(reservation3);
		Reservation reservation4 = new Reservation(10.0, dateAndTime4, 4, user1);
		reservationService.add(reservation4);
		Reservation reservation5 = new Reservation(10.0, dateAndTime4, 4, null);
		reservationService.add(reservation5);
		
		PropNew propnew1 = new PropNew(place1, "rekbioskop1", "opsi opis", "");
		propNewService.add(propnew1);
		
		PropUsed propused1 = new PropUsed(user1, "koriscenirek1", "opis opis opis", LocalDateTime.now(ZoneId.of("Z")), "");
		propused1.setApproved(true);
		propUsedService.add(propused1);
		
		User user3 = new User("fan@fan.com", "dddddd", "Fan", "Zone", "Ddd", null);
		user3.setUserType(UserType.FANZONEADMIN);
		userService.register(user3);
	}
}
