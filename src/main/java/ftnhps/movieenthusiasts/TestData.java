package ftnhps.movieenthusiasts;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.info.ProjectInfoAutoConfiguration;
import org.springframework.stereotype.Component;

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
				"{projectionTimes:[\"12:30\",\"15:40\"]}",
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
				"{projectionTimes:[\"12:30\",\"15:40\"]}",
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
				"{projectionTimes:[\"22:30\",\"23:40\"]}",
				333.00);
		projectionService.add(projection3);
	}
}
