package ftnhps.movieenthusiasts.projectiosTest;

import static ftnhps.movieenthusiasts.testingConstants.ProjectionConstants.DB_ID;
import static ftnhps.movieenthusiasts.testingConstants.ProjectionConstants.DB_NAME;
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

import ftnhps.movieenthusiasts.places.Place;
import ftnhps.movieenthusiasts.projections.Projection;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectionControllerTest {
	
	private static final String URL_PREFIX = "/api/projections";
	private static final Object DB_GENRE = "Drama";
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
	public void testGetProjection() throws Exception{
		mockMvc.perform(get(URL_PREFIX+"/"+ DB_ID)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(DB_ID))
		.andExpect(jsonPath("$.name").value(DB_NAME))
		.andExpect(jsonPath("$.genre").value(DB_GENRE));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddProjection() throws Exception{
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
		String json = json(projection1);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isForbidden());
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testEditProjection() throws Exception{
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
		projection1.setId(new Long(DB_ID) );
		String json = json(projection1);
		this.mockMvc.perform(post(URL_PREFIX+"/"+DB_ID).contentType(contentType).content(json)).andExpect(status().isMethodNotAllowed());
	}
	
	public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
    }
	
	
}
