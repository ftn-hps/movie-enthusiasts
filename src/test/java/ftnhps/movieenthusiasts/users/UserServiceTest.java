package ftnhps.movieenthusiasts.users;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	public void testFindOne() {
		User dbUser = userService.findOne(UserConstants.DB_ID);
		assertThat(dbUser).isNotNull();

		assertThat(dbUser.getId()).isEqualTo(UserConstants.DB_ID);
		assertThat(dbUser.getEmail()).isEqualTo(UserConstants.DB_EMAIL);
		assertThat(dbUser.getName()).isEqualTo(UserConstants.DB_NAME);
		assertThat(dbUser.getLastName()).isEqualTo(UserConstants.DB_LAST_NAME);
		assertThat(dbUser.getCity()).isEqualTo(UserConstants.DB_CITY);
	}

	@Test
	public void testFindAll() {
		List<User> users = userService.findAll();
		assertThat(users).hasSize(UserConstants.DB_COUNT);
	}

	@Test
	@Transactional
	public void testRegister() {
		User user = new User(UserConstants.NEW_EMAIL,
				UserConstants.NEW_PASSWORD,
				UserConstants.NEW_NAME,
				UserConstants.NEW_LAST_NAME,
				UserConstants.NEW_CITY,
				null);
		user.setUserType(UserType.VISITOR);
		user.setEmailConfirmed(true);

		int dbSizeBeforeAdd = userService.findAll().size();
		User dbUser = userService.register(user);
		assertThat(dbUser).isNotNull();

		List<User> users = userService.findAll();
		assertThat(users).hasSize(dbSizeBeforeAdd + 1);
		dbUser = users.get(users.size() - 1);
		assertThat(dbUser.getEmail()).isEqualTo(UserConstants.NEW_EMAIL);
		assertThat(dbUser.getPassword()).isEqualTo(UserConstants.NEW_PASSWORD);
		assertThat(dbUser.getName()).isEqualTo(UserConstants.NEW_NAME);
		assertThat(dbUser.getLastName()).isEqualTo(UserConstants.NEW_LAST_NAME);
		assertThat(dbUser.getCity()).isEqualTo(UserConstants.NEW_CITY);
	}

	@Test
	@Transactional
	public void testEdit() {
		User dbUser = userService.findOne(UserConstants.DB_ID);

		dbUser.setPassword(UserConstants.NEW_PASSWORD);
		dbUser.setName(UserConstants.NEW_NAME);
		dbUser.setLastName(UserConstants.NEW_LAST_NAME);
		dbUser.setCity(UserConstants.NEW_CITY);

		dbUser = userService.edit(dbUser.getId(), dbUser);
		assertThat(dbUser).isNotNull();

		dbUser = userService.findOne(UserConstants.DB_ID);
		assertThat(dbUser.getPassword()).isEqualTo(UserConstants.NEW_PASSWORD);
		assertThat(dbUser.getName()).isEqualTo(UserConstants.NEW_NAME);
		assertThat(dbUser.getLastName()).isEqualTo(UserConstants.NEW_LAST_NAME);
		assertThat(dbUser.getCity()).isEqualTo(UserConstants.NEW_CITY);
	}

	@Test
	@Transactional
	public void testRewardUser() {
		User dbUser = userService.findOne(UserConstants.DB_ID);
		int oldPoints = dbUser.getPoints();

		dbUser = userService.rewardUser(dbUser.getId(), 5);
		
		assertThat(dbUser.getPoints()).isEqualTo(oldPoints + 5);
	}

}