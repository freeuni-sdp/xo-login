

import static org.junit.Assert.*;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import ge.edu.freeuni.sdp.xo.login.LoginInformation;
import ge.edu.freeuni.sdp.xo.login.LoginService;
import ge.edu.freeuni.sdp.xo.login.Token;
import ge.edu.freeuni.sdp.xo.login.UserInformation;
import ge.edu.freeuni.sdp.xo.login.UserName;
import org.junit.Test;
import org.mockito.Mockito;

public class LoginServiceTest {
	
	@Test
	public void testLoginRegisteredUser() {
		LoginService loginService = new LoginService();
		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
		userInfo.username = "sandro";
		userInfo.password = "blabla";
		Mockito.when(userInfo.toString()).thenReturn("sandro, blabla");
		Token token = Mockito.mock(Token.class);
		token = loginService.loginUser(userInfo);
		assertEquals(token.token, "00000");
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
		LoginService loginService = new LoginService();
		UserName userName = Mockito.mock(UserName.class);
		userName = loginService.getUserByToken("00000");
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
		assertEquals(response.getStatus(), 201);
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
		assertEquals(response.getStatus(), 400);
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
		assertEquals(response.getStatus(), 400);
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
		assertEquals(response.getStatus(), 400);
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