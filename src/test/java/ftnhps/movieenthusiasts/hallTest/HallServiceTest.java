package ftnhps.movieenthusiasts.hallTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.hall.HallService;
import ftnhps.movieenthusiasts.places.Place;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HallServiceTest {

	private static final int DB_COUNT = 3;
	private static final Long DB_ID = (long) 1;
	private static final String DB_NAME = "sala1";

	@Autowired
	private HallService hallService;
	
	@Test
	public void testFindAll() {
		List<Hall> halls = hallService.findAll();
		assertThat(halls).hasSize(DB_COUNT);
	}
	
	@Test
	public void testFindOne() {
		Hall halls = hallService.findOne(DB_ID);
		assertThat(halls).isNotNull();
		assertThat(halls.getName()).isEqualTo(DB_NAME);
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testAdd() {
		Hall hall = hallService.findOne(DB_ID);
		assertThat(hall).isNotNull();
		
		List<Hall> halls = hallService.findAll();
		assertThat(halls).hasSize(DB_COUNT);
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testEdit() {
		Hall hall = hallService.findOne(DB_ID);
		hall.setName("TEST");
		
		hall = hallService.edit(hall.getId(),hall);
		assertThat(hall).isNotNull();
		
		assertThat(hall.getName()).isEqualTo("TEST");
	}
}
