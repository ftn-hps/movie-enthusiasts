package ftnhps.movieenthusiasts.PlaceTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;
import ftnhps.movieenthusiasts.places.PlaceType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceServiceTest {

	private static final int DB_COUNT = 3;
	private static final Long DB_ID = (long)1;
	private static final String DB_NAME = "Arena Cineplex";
	private static final String DB_NAME_NEW = "TEST";
	@Autowired
	private PlaceService placeService;
	
	@Test
	public void testFindAll() {
		List<Place> students = placeService.findAll();
		assertThat(students).hasSize(DB_COUNT ); 
	}
	
	@Test
	public void testFindOne() {
		Place place = placeService.findOne(DB_ID);
		assertThat(place).isNotNull();
		assertThat(place.getName()).isEqualTo(DB_NAME); 
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testAdd() {
		Place place1 = new Place(PlaceType.CINEMA,
				"Arena Cineplex",
				4,
				"Novi Sad",
				"Arena Cineplex je kompletno renovirana 2010. godine u skladu sa najnovijim svetskim standardima.", 
				45.262143, 19.831931);
		int before = placeService.findAll().size();
		Place place = placeService.add(place1);
		List<Place> places = placeService.findAll();
		assertThat(place).isNotNull();
		assertThat(places).hasSize(before + 1);
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testEdit() {
		Place place = placeService.findOne(DB_ID);
		assertThat(place).isNotNull();
		assertThat(place.getName()).isEqualTo(DB_NAME);
		
		place.setName(DB_NAME_NEW);
		place = placeService.edit(place.getId(),place);
		assertThat(place).isNotNull();
		assertThat(place.getName()).isEqualTo(DB_NAME_NEW);
	}
	
}
