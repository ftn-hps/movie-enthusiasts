package ftnhps.movieenthusiasts.DateTimeTest;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import ftnhps.movieenthusiasts.DateAndTime.DateAndTime;
import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.projections.Projection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DateTimeControllerTest {
	private static final String URL_PREFIX = "/api/dateAndTimeOfProjections";

	private static final Object DB_ID = 1;

	private static final Object DB_LAYOUT = "ooooooooo";
	
	private MockMvc mockMvc;
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@Autowired
	private WebApplicationContext webApplicationContext;
	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetAllDateAndTime() throws Exception{
		mockMvc.perform(get(URL_PREFIX) ).andExpect(status().isOk())
		.andExpect(jsonPath("$.[*].id").value(hasItem(DB_ID)))
		.andExpect(jsonPath("$.[*].reservationLayout").value(hasItem(DB_LAYOUT)));
	}
	
	@Test
	public void testGetOneDateAndTime() throws Exception{
		mockMvc.perform(get(URL_PREFIX+"/"+ 5) ).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(5))
		.andExpect(jsonPath("$.reservationLayout").value(DB_LAYOUT));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddDateTime() throws Exception{
		Projection projection1 = new Projection();
		projection1.setId(new Long(1));
		Hall hall1 = new Hall();
		hall1.setId(new Long(1));
		DateAndTime dateAndTime1 = new DateAndTime(
				new Long(1520352000),
				"rooooooooooo",
				projection1,
				hall1);
		String json = json(dateAndTime1);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isForbidden());
	}
	
	public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
    }
}
