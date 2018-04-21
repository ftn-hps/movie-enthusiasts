package ftnhps.movieenthusiasts.projectiosTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.intThat;
import static ftnhps.movieenthusiasts.testingConstants.ProjectionConstants.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionService;
import ftnhps.movieenthusiasts.users.UserService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectionServiceTest {

	@Autowired
	private ProjectionService projectionService;
	@Autowired
	private UserService userService;
	
	
	@Test
	public void testFindAll() {
		List<Projection> students = projectionService.findAll();
		assertThat(students).hasSize(DB_COUNT_PROJECTIONS ); 
	}
	
	@Test
	public void TestFindOne()
	{
		Projection projection = projectionService.findOne(new Long(DB_ID));
		
		assertThat(projection).isNotNull();
		assertThat(projection.getName()).isEqualTo(DB_NAME);
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testAdd() {
		Place place = new Place();
		place .setId(new Long(1));
		Projection projection1 = new Projection(place,
				"Test",
				"Test",
				"Drama", 
				"Test",
				120, 
				"/img/placeholder.png",
				3, 
				"Test",
				333.00);
		
		int sizeBefore =  projectionService.findAll().size();
		
		Projection projection = projectionService.add(projection1);
		assertThat(projection).isNotNull();
		
		
		List<Projection> projections = projectionService.findAll();
		assertThat(projections).hasSize(sizeBefore+1);
		
		projection = projectionService.findOne(projection.getId());
		assertThat(projection.getName()).isEqualTo("Test");
		assertThat(projection.getGenre()).isEqualTo("Drama");
		
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testEdit() {
		
		Projection projection = projectionService.findOne(new Long(DB_ID));
		
		assertThat(projection).isNotNull();
		assertThat(projection.getName()).isEqualTo(DB_NAME);
		
		projection.setName(DB_NEW_NAME);
		projection.setShortDescription(DB_NEW_DESCRIPTION);
		
		projectionService.edit(projection.getId(), projection);
		
		projection = projectionService.findOne(new Long(DB_ID));
		assertThat(projection.getName()).isEqualTo(DB_NEW_NAME);
		assertThat(projection.getShortDescription()).isEqualTo(DB_NEW_DESCRIPTION);
		
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testDelete() {
		
	int numberBefore = projectionService.findAll().size();
	projectionService.delete(new Long(DB_COUNT_PROJECTIONS));
	List<Projection> projections = projectionService.findAll();
	assertThat(projections).hasSize(numberBefore - 1);
	
	Projection projection = projectionService.findOne(new Long(DB_COUNT_PROJECTIONS));
	assertThat(projection).isNull();
	
	}
	
}
