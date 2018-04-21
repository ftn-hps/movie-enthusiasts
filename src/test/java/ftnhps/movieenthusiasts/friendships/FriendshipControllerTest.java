package ftnhps.movieenthusiasts.friendships;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FriendshipControllerTest {

	private static final String URL_PREFIX = "/api/friendships/";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetAll() throws Exception {
		this.mockMvc.perform(get(URL_PREFIX))
			.andExpect(status().is(403));
	}

	@Test
	@Transactional
	public void testAdd() throws Exception {
		this.mockMvc.perform(post(URL_PREFIX + FriendshipConstants.DB_RECEIVER_ID))
			.andExpect(status().is(403));
	}
	
	@Test
	@Transactional
	public void testAccept() throws Exception {
		this.mockMvc.perform(put(URL_PREFIX + FriendshipConstants.DB_ID))
			.andExpect(status().is(403));
	}
	
	@Test
	@Transactional
	public void testRemove() throws Exception {
		this.mockMvc.perform(delete(URL_PREFIX + FriendshipConstants.DB_ID))
			.andExpect(status().is(403));
	}
}
