package ftnhps.movieenthusiasts.projectiosTest;

import static org.assertj.core.api.Assertions.assertThat;
import static ftnhps.movieenthusiasts.testingConstants.ProjectionConstants.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ftnhps.movieenthusiasts.projections.Projection;
import ftnhps.movieenthusiasts.projections.ProjectionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectionServiceTest {

	@Autowired
	private ProjectionService projectionService;
	
	@Test
	public void testFindAll() {
		List<Projection> students = projectionService.findAll();
		assertThat(students).hasSize(DB_COUNT_PROJECTIONS); 
	}
}
