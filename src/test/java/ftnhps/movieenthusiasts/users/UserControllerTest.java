package ftnhps.movieenthusiasts.users;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import ftnhps.movieenthusiasts.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	private static final String URL_PREFIX = "/api/user-auth/";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@PostConstruct
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	@Transactional
	public void testRegister() throws Exception {
		HashMap<String, String> user = new HashMap<>();
		user.put("email", UserConstants.NEW_EMAIL);
		user.put("password", UserConstants.NEW_PASSWORD);
		String json = TestUtils.json(user);
		this.mockMvc.perform(post(URL_PREFIX)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json))
		.andExpect(status().isOk());
	}
}
