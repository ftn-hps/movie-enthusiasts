package ftnhps.movieenthusiasts.hallTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import ftnhps.movieenthusiasts.hall.Hall;
import ftnhps.movieenthusiasts.places.Place;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HallControllerTest {

	private static final String URL_PREFIX = "/api/halls";
	private static final Object DB_NAME = "sala1";
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
	public void testGetHall() throws Exception{
		mockMvc.perform(get(URL_PREFIX+"/"+ DB_ID)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(DB_ID))
		.andExpect(jsonPath("$.name").value(DB_NAME));
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testAddHall() throws Exception{
		Place place = new Place();
		place .setId(new Long(1)); 
		Hall hall1 = new Hall("sala1",3,4,"oooooooooooo",place);
		String json = json(hall1);
		this.mockMvc.perform(post(URL_PREFIX).contentType(contentType).content(json)).andExpect(status().isForbidden());
	
	}
	
	@Test
	@Transactional
	@Rollback(true)
	public void testEditHall() throws Exception{
		Place place = new Place();
		place .setId(new Long(1)); 
		Hall hall1 = new Hall("sala1",3,4,"oooooooooooo",place);
		hall1.setId(DB_ID);
		
		String json = json(hall1);
		this.mockMvc.perform(put(URL_PREFIX+"/"+DB_ID).contentType(contentType).content(json)).andExpect(status().isMethodNotAllowed());
	
	}
	
	
	
	
	public static String json(Object object)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        
        return mapper.writeValueAsString(object);
        
    }
	
}
