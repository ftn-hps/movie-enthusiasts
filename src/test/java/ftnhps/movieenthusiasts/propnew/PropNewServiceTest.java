package ftnhps.movieenthusiasts.propnew;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.fanzone.propnew.PropNew;
import ftnhps.movieenthusiasts.fanzone.propnew.PropNewService;
import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.places.PlaceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropNewServiceTest {
	
	@Autowired
	PropNewService propNewService;
	@Autowired
	PlaceService placeService;
	
	@Test
	public void testFindOne() {
		PropNew dbProp = propNewService.findOne(PropNewConstants.DB_ID);
		assertThat(dbProp).isNotNull();
		
		assertThat(dbProp.getId()).isEqualTo(PropNewConstants.DB_ID);
		assertThat(dbProp.getPlace().getId()).isEqualTo(PropNewConstants.DB_PLACEID);
		assertThat(dbProp.getName()).isEqualTo(PropNewConstants.DB_NAME);
		assertThat(dbProp.getDescription()).isEqualTo(PropNewConstants.DB_DESCRIPTION);
		assertThat(dbProp.getImagePath()).isEqualTo(PropNewConstants.DB_IMAGE);
		assertThat(dbProp.getDeleted()).isEqualTo(PropNewConstants.DB_DELETED);
	}
	
	@Test
	public void testFindAll() {
		List<PropNew> props = propNewService.findAll();
		assertThat(props).hasSize(PropNewConstants.DB_COUNT);
	}
	
	
	@Test
	@Transactional
	public void testAdd() {
		Place place = placeService.findOne(PropNewConstants.NEW_PLACEID);
		PropNew prop = new PropNew(place, PropNewConstants.NEW_NAME, PropNewConstants.NEW_DESCRIPTION, PropNewConstants.NEW_IMAGE);
		
		int dbSizeBefore = propNewService.findAll().size();
		PropNew dbProp = propNewService.add(prop);
		assertThat(dbProp).isNotNull();
		
		List<PropNew> props = propNewService.findAll();
		assertThat(props).hasSize(dbSizeBefore + 1);
		dbProp = propNewService.findOne(dbProp.getId());
		assertThat(dbProp.getPlace().getId()).isEqualTo(PropNewConstants.NEW_PLACEID);
		assertThat(dbProp.getName()).isEqualTo(PropNewConstants.NEW_NAME);
		assertThat(dbProp.getDescription()).isEqualTo(PropNewConstants.NEW_DESCRIPTION);
		assertThat(dbProp.getImagePath()).isEqualTo(PropNewConstants.NEW_IMAGE);
		assertThat(dbProp.getDeleted()).isEqualTo(PropNewConstants.NEW_DELETED);
	}
	
	@Test
	@Transactional
	public void testEdit() {
		PropNew dbProp = propNewService.findOne(PropNewConstants.DB_ID);
		
		dbProp.setName(PropNewConstants.NEW_NAME);
		dbProp.setDescription(PropNewConstants.NEW_DESCRIPTION);
		dbProp.setImagePath(PropNewConstants.NEW_IMAGE);
		
		dbProp = propNewService.edit(dbProp.getId(), dbProp);
		assertThat(dbProp).isNotNull();
		assertThat(dbProp.getName()).isEqualTo(PropNewConstants.NEW_NAME);
		assertThat(dbProp.getDescription()).isEqualTo(PropNewConstants.NEW_DESCRIPTION);
		assertThat(dbProp.getImagePath()).isEqualTo(PropNewConstants.NEW_IMAGE);
	}
	
}
