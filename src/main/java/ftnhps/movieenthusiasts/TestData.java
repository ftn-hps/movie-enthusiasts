package ftnhps.movieenthusiasts;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;
import ftnhps.movieenthusiasts.places.PlaceType;
import ftnhps.movieenthusiasts.users.User;
import ftnhps.movieenthusiasts.users.UserService;

@Component
public class TestData {

	@Autowired
	private UserService userService;
	@Autowired
	private PlaceService placeService;
	
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
	}
}
