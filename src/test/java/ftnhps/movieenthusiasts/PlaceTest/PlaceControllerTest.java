package ftnhps.movieenthusiasts.PlaceTest;

import static ftnhps.movieenthusiasts.testingConstants.ProjectionConstants.DB_ID;
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
import ftnhps.movieenthusiasts.places.PlaceType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlaceControllerTest {

	private static final String URL_PREFIX = "/api/places";
	private static final String DB_NAME = "Arena Cineplex";
	private static final Long DB_ID = (long)1;
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
	public void testGet() throws Exception{
		mockMvc.perform(get(URL_PREFIX+"/"+ DB_ID)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(DB_ID))
		.andExpect(jsonPath("$.name").value(DB_NAME));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAdd() throws Exception{
		Place place1 = new Place(PlaceType.CINEMA,
				"Arena Cineplex",
				4,
				"Novi Sad",
				"Arena Cineplex je kompletno renovirana 2010. godine u skladu sa najnovijim svetskim standardima.", 
				45.262143, 19.831931);
		String json = json(place1);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isForbidden());
	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testEditProjection() throws Exception{
		
		Place place1 = new Place(PlaceType.CINEMA,
				"Arena Cineplex",
				4,
				"Novi Sad",
				"Arena Cineplex je kompletno renovirana 2010. godine u skladu sa najnovijim svetskim standardima.", 
				45.262143, 19.831931);
		
		place1.setId(DB_ID);
		String json = json(place1);
		this.mockMvc.perform(post(URL_PREFIX+"/"+DB_ID).contentType(contentType).content(json)).andExpect(status().isMethodNotAllowed());
	
		
	}
	
	
	public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
    }
}
