import static org.junit.Assert.*;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ge.edu.freeuni.sdp.xo.login.LoginInformation;
import ge.edu.freeuni.sdp.xo.login.LoginService;
import ge.edu.freeuni.sdp.xo.login.Token;
import ge.edu.freeuni.sdp.xo.login.UserInformation;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mockito;

import com.microsoft.azure.storage.StorageException;

public class LoginServiceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(LoginService.class);
	}

	@Test
	public void testLoginUser400Expected() {
		LoginInformation loginInfo = new LoginInformation();
		loginInfo.username = "giorgi";
		loginInfo.password = null;
		Response actual = target("/login").request().put(
				Entity.entity(loginInfo, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test(expected = WebApplicationException.class)
	public void testLoginUserPasswordNull() {
		LoginService loginService = new LoginService();
		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
		userInfo.username = "sandro";
		userInfo.password = null;
		Token token = Mockito.mock(Token.class);
		try {
			token = loginService.loginUser(userInfo);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(token, null);
	}

	@Test(expected = WebApplicationException.class)
	public void testLoginUserUsernameNull() {
		LoginService loginService = new LoginService();
		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
		userInfo.username = null;
		userInfo.password = "blabla";
		Token token = Mockito.mock(Token.class);
		try {
			token = loginService.loginUser(userInfo);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(token, null);
	}

	@Test
	public void testCreateUserUsernameNull() {
		LoginService loginService = new LoginService();
		UserInformation user = Mockito.mock(UserInformation.class);
		user.username = null;
		user.password = "blabla";
		user.email = "giorgi@freeuni.edu.ge";
		Response response = Mockito.mock(Response.class);
		try {
			response = loginService.createUser(user);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(response.getStatus(),
				Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void testCreateUserPasswordNull() {
		LoginService loginService = new LoginService();
		UserInformation user = Mockito.mock(UserInformation.class);
		user.username = "Giorgi";
		user.password = null;
		user.email = "giorgi@freeuni.edu.ge";
		Response response = Mockito.mock(Response.class);
		try {
			response = loginService.createUser(user);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(response.getStatus(),
				Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void testCreateUserEmailNull() {
		LoginService loginService = new LoginService();
		UserInformation user = Mockito.mock(UserInformation.class);
		user.username = "Giorgi";
		user.password = "blabla";
		user.email = null;
		Response response = Mockito.mock(Response.class);
		try {
			response = loginService.createUser(user);
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(response.getStatus(),
				Response.Status.BAD_REQUEST.getStatusCode());
	}

	@Test
	public void testCreateUserEmailNull404Expected() {
		UserInformation user = new UserInformation();
		user.username = "Giorgi";
		user.password = "blabla";
		user.email = null;
		Response actual = target("/login/users").request().post(
				Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testCreateUserPasswordNull404Expected() {
		UserInformation user = new UserInformation();
		user.username = "Giorgi";
		user.password = null;
		user.email = "giorgi@freeuni.edu.ge";
		Response actual = target("/login/users").request().post(
				Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testCreateUserNameNull404Expected() {
		UserInformation user = new UserInformation();
		user.username = null;
		user.password = "blabla";
		user.email = "giorgi@freeuni.edu.ge";
		Response actual = target("/login/users").request().post(
				Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

}
