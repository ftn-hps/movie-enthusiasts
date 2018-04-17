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
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserService;

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
	
	
	@PostConstruct
	private void init() {
		User user1 = new User("ddd@ddd.com", "dddddd", "Ddd", "Ddd", "Ddd", null);
		userService.register(user1);
		
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
				"/",
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
				"/",
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
				"/",
				5, 
				"Bbbbbbbbb",
				333.00);
		projectionService.add(projection3);
		
		Hall hall1 = new Hall("sala1",3,4,"layout",place1);
		hallService.add(hall1);
		
		Hall hall2 = new Hall("sala2",3,3,"layout",place1);
		hallService.add(hall2);
		
		DateAndTime dateAndTime = new DateAndTime(
				new Long(946684800),
				"oooooooooooooooooooooooo",
				projection1,
				hall1);
		dateAndTimeOfProjectionService.add(dateAndTime);
		
		PropNew propnew1 = new PropNew(place1.getId(), "rekbioskop1", "opsi opis", "");
		propNewService.add(propnew1);
		
		PropUsed propused1 = new PropUsed(user1.getId(), "koriscenirek1", "opis opis opis", LocalDateTime.now(ZoneId.of("Z")), "");
		propused1.setApproved(true);
		propUsedService.add(propused1);
	}
}
