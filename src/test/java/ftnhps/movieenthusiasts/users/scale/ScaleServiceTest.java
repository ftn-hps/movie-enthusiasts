package ftnhps.movieenthusiasts.users.scale;

import static org.assertj.core.api.Assertions.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScaleServiceTest {

	@Autowired
	ScaleService scaleService;
	
	@Test
	public void testGetActive() {
		Scale scale = scaleService.getActive();
		assertThat(scale).isNotNull();
		
		assertThat(scale.getSilver()).isEqualTo(ScaleConstants.DB_SILVER);
		assertThat(scale.getGold()).isEqualTo(ScaleConstants.DB_GOLD);
		assertThat(scale.getActive()).isEqualTo(ScaleConstants.DB_ACTIVE);
	}
	
	@Test
	@Transactional
	public void testSetActive() {
		Scale scale = new Scale(ScaleConstants.NEW_SILVER, ScaleConstants.NEW_GOLD);
		Scale scaleBefore = scaleService.getActive();
		scaleService.setActive(scale);
		scale = scaleService.getActive();
		
		assertThat(scale.getId()).isNotEqualTo(scaleBefore.getId());
		assertThat(scale.getSilver()).isEqualTo(ScaleConstants.NEW_SILVER);
		assertThat(scale.getGold()).isEqualTo(ScaleConstants.NEW_GOLD);
		assertThat(scale.getActive()).isEqualTo(ScaleConstants.NEW_ACTIVE);
	}
	
}
