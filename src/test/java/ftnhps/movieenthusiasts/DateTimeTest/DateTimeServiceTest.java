package ftnhps.movieenthusiasts.DateTimeTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.DateAndTime.DateAndTimeService;
import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTimeServiceTest {

	private static final int DB_COUNT_DATETIME = 5;
	private static final int DB_ID = 1;
	private static final String DB_LAYOUT = "rooooooooooo";
	private static final int DB_ID_DELETE = 5;
	
	@Autowired 
	private DateAndTimeService dateAndTimeService;
	
	@Test
	public void testFindAll() {
		List<DateAndTime> students = dateAndTimeService.findAll();
		assertThat(students).hasSize(DB_COUNT_DATETIME); 
	}
	
	@Test
	public void TestFindOne()
	{
		DateAndTime dateAndTime = dateAndTimeService.findOne(new Long(DB_ID));
		
		assertThat(dateAndTime).isNotNull();
		assertThat(dateAndTime.getReservationLayout()).isEqualTo(DB_LAYOUT);
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testAdd() {
		Projection projection1 = new Projection();
		projection1.setId(new Long(1));
		Hall hall1 = new Hall();
		hall1.setId(new Long(1));
		DateAndTime dateAndTime1 = new DateAndTime(
				new Long(1520352000),
				"rooooooooooo",
				projection1,
				hall1);
			
		int sizeBefore = dateAndTimeService.findAll().size();
		DateAndTime  dateAndTime = dateAndTimeService.add(dateAndTime1);
		assertThat(dateAndTime).isNotNull();
		
		List<DateAndTime> dateAndTimes = dateAndTimeService.findAll();
		assertThat(dateAndTimes).hasSize(sizeBefore +1 ); 
		
		
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testEdit() {
		
		DateAndTime dateAndTime = dateAndTimeService.findOne(new Long(DB_ID));
		assertThat(dateAndTime).isNotNull();
		dateAndTime.setReservationLayout(DB_LAYOUT);
		dateAndTime = dateAndTimeService.edit(dateAndTime.getId(),dateAndTime);
		assertThat(dateAndTime).isNotNull();
		
		assertThat(dateAndTime).isNotNull();
		assertThat(dateAndTime.getReservationLayout()).isEqualTo(DB_LAYOUT); 
			
	}
	
	@Test
    @Transactional
    @Rollback(true) 
	public void testDelete() {
		
		int sizeBefore = dateAndTimeService.findAll().size();
		DateAndTime dateAndTime = dateAndTimeService.findOne(new Long(DB_ID_DELETE));
		dateAndTimeService.remove(new Long(DB_ID_DELETE));
		assertThat(dateAndTime).isNotNull();
		
		List<DateAndTime> dateAndTimes = dateAndTimeService.findAll();
		assertThat(dateAndTimes).hasSize(sizeBefore - 1 ); 
		
		
	}
}
