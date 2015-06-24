import static org.junit.Assert.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.*;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

import ge.edu.freeuni.sdp.xo.login.*;

import org.junit.Test;
import org.mockito.Mockito;

public class LoginServiceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(LoginService.class);
	}

	@Test
	public void testLoginRegisteredUser() {
		LoginInformation loginInfo = new LoginInformation();
		loginInfo.username = "sandro";
		loginInfo.password = "blabla";

		Token actualToken = target("/login").request().put(Entity.entity(loginInfo, MediaType.APPLICATION_JSON_TYPE)).readEntity(Token.class);;
		assertFalse(actualToken.token.isEmpty());
	}


	@Test(expected=WebApplicationException.class)
	public void testLoginUserPasswordNull() {
		LoginService loginService = new LoginService();
		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
		userInfo.username = "sandro";
		userInfo.password = null;
		Token token = Mockito.mock(Token.class);
		token = loginService.loginUser(userInfo);
		assertEquals(token, null);
	}

	@Test(expected=WebApplicationException.class)
	public void testLoginUserUsernameNull() {
		LoginService loginService = new LoginService();
		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
		userInfo.username = null;
		userInfo.password = "blabla";
		Token token = Mockito.mock(Token.class);
		token = loginService.loginUser(userInfo);
		assertEquals(token, null);
	}

	@Test(expected=WebApplicationException.class)
	public void testLoginUserPasswordIncorrect() {
		LoginService loginService = new LoginService();
		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
		userInfo.username = "sandro";
		userInfo.password = "blublu";
		Token token = Mockito.mock(Token.class);
		token = loginService.loginUser(userInfo);
		assertEquals(token, null);
	}

	@Test(expected=WebApplicationException.class)
	public void testLoginUserUsernameIncorrect() {
		LoginService loginService = new LoginService();
		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
		userInfo.username = "sandro1";
		userInfo.password = "blublu";
		Token token = Mockito.mock(Token.class);
		token = loginService.loginUser(userInfo);
		assertEquals(token, null);
	}

	@Test
	public void testGetUserByTokenRegistered() {
		UserName userName = target("/login/00000").request().get().readEntity(UserName.class);;
		assertEquals(userName.username, "sandro");
	}

	@Test(expected=WebApplicationException.class)
	public void testGetUserByTokenNotRegistered() {
		LoginService loginService = new LoginService();
		UserName userName = Mockito.mock(UserName.class);
		userName = loginService.getUserByToken("00001");
		assertEquals(userName, null);
	}

	@Test
	public void testCreateUser() {
		LoginService loginService = new LoginService();
		UserInformation user = Mockito.mock(UserInformation.class);
		user.username = "Giorgi";
		user.password = "blabla";
		user.email = "giorgi@freeuni.edu.ge";
		Response response = Mockito.mock(Response.class);
		response = loginService.createUser(user);
		assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
	}

	@Test
	public void testCreateUserUsernameNull() {
		LoginService loginService = new LoginService();
		UserInformation user = Mockito.mock(UserInformation.class);
		user.username = null;
		user.password = "blabla";
		user.email = "giorgi@freeuni.edu.ge";
		Response response = Mockito.mock(Response.class);
		response = loginService.createUser(user);
		assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void testCreateUserPasswordNull() {
		LoginService loginService = new LoginService();
		UserInformation user = Mockito.mock(UserInformation.class);
		user.username = "Giorgi";
		user.password = null;
		user.email = "giorgi@freeuni.edu.ge";
		Response response = Mockito.mock(Response.class);
		response = loginService.createUser(user);
		assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void testCreateUserEmailNull() {
		LoginService loginService = new LoginService();
		UserInformation user = Mockito.mock(UserInformation.class);
		user.username = "Giorgi";
		user.password = "blabla";
		user.email = null;
		Response response = Mockito.mock(Response.class);
		response = loginService.createUser(user);
		assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void testGetUserRegistered() {
		LoginService loginService = new LoginService();
		UserInformation userInfo = Mockito.mock(UserInformation.class);
		userInfo = loginService.getUser("anna");
		assertEquals(userInfo.username, "anna");
		assertEquals(userInfo.password, "blabla");
		assertEquals(userInfo.email, "agoro12@freeuni.edu.ge");
	}

	@Test(expected=WebApplicationException.class)
	public void testGetUserNotRegistered() {
		LoginService loginService = new LoginService();
		UserInformation userInfo = Mockito.mock(UserInformation.class);
		userInfo = loginService.getUser("Giorgi");
		assertEquals(userInfo, null);
	}

}
