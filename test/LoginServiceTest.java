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
import ge.edu.freeuni.sdp.xo.login.UserName;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import org.mockito.Mockito;

import com.microsoft.azure.storage.StorageException;

public class LoginServiceTest extends JerseyTest{
	
	@Override
	 protected Application configure() {
		 return new ResourceConfig(LoginService.class);
	 }
	
	
	@Test
	public void testUsers(){
//		LoginService login = new LoginService();
//		
//		
//		UserInformation user = new UserInformation();
//		user.email = "slezh12@freeuni.edu.ge";
//		user.password = "sandro";
//		user.username = "sandro";
//		
//		try {
//			login.createUser(user);
//			System.out.println("jigrulad chaiaraa");
//		} catch (StorageException e) {
//			// TODO Auto-generated catch block
//			System.out.println("ak vaaar");
//			e.printStackTrace();
//		}
//		try {
//			UserInformation result = login.getUser("sandro");
//			if(result != null){
//				System.out.println(result.toString());
//			} else {
//				System.out.print("nulliaa");
//			}
//		} catch (StorageException e) {
//			// TODO Auto-generated catch block
//			System.out.println("erroria ar daizmende");
//			e.printStackTrace();
//		}
		
		
	}
	
//	@Test
//	public void testLoginRegisteredUser() {
//		LoginInformation loginInfo = new LoginInformation();
//		loginInfo.username = "sandro";
//		loginInfo.password = "blabla";
//
//		Token actualToken = target("/login").request().put(Entity.entity(loginInfo, MediaType.APPLICATION_JSON_TYPE)).readEntity(Token.class);;
//		assertFalse(actualToken.token.isEmpty());
//	}
//	
//	@Test
//	public void testLoginRegisteredUser200Expected() {
//		LoginInformation loginInfo = new LoginInformation();
//		loginInfo.username = "sandro";
//		loginInfo.password = "blabla";
//		Response actual = target("/login").request().put(Entity.entity(loginInfo, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
//	}
//	
//	@Test
//	public void testLoginNotRegisteredUser422Expected() {
//		LoginInformation loginInfo = new LoginInformation();
//		loginInfo.username = "giorgi";
//		loginInfo.password = "blabla";
//		Response actual = target("/login").request().put(Entity.entity(loginInfo, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(422, actual.getStatus());
//	}
//	
//	@Test
//	public void testLoginUser400Expected() {
//		LoginInformation loginInfo = new LoginInformation();
//		loginInfo.username = "giorgi";
//		loginInfo.password = null;
//		Response actual = target("/login").request().put(Entity.entity(loginInfo, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actual.getStatus());
//	}

//	@Test(expected=WebApplicationException.class)
//	public void testLoginUserPasswordNull() {
//		LoginService loginService = new LoginService();
//		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
//		userInfo.username = "sandro";
//		userInfo.password = null;
//		Token token = Mockito.mock(Token.class);
//		token = loginService.loginUser(userInfo);
//		assertEquals(token, null);
//	}
//
//	@Test(expected=WebApplicationException.class)
//	public void testLoginUserUsernameNull() {
//		LoginService loginService = new LoginService();
//		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
//		userInfo.username = null;
//		userInfo.password = "blabla";
//		Token token = Mockito.mock(Token.class);
//		token = loginService.loginUser(userInfo);
//		assertEquals(token, null);
//	}
//
//	@Test(expected=WebApplicationException.class)
//	public void testLoginUserPasswordIncorrect() {
//		LoginService loginService = new LoginService();
//		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
//		userInfo.username = "sandro";
//		userInfo.password = "blublu";
//		Token token = Mockito.mock(Token.class);
//		token = loginService.loginUser(userInfo);
//		assertEquals(token, null);
//	}
//
//	@Test(expected=WebApplicationException.class)
//	public void testLoginUserUsernameIncorrect() {
//		LoginService loginService = new LoginService();
//		LoginInformation userInfo = Mockito.mock(LoginInformation.class);
//		userInfo.username = "sandro1";
//		userInfo.password = "blublu";
//		Token token = Mockito.mock(Token.class);
//		token = loginService.loginUser(userInfo);
//		assertEquals(token, null);
//	}

//	@Test
//	public void testGetUserByTokenRegistered() {
//		UserName userName = target("/login/00000").request().get().readEntity(UserName.class);;
//		assertEquals(userName.username, "sandro");
//	}
//	
//	@Test
//	public void testGetUserByToken200Expected() {
//		Response actual = target("/login/00000").request().get();
//		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
//	}
//	
//	@Test
//	public void testGetUserByToken404Expected() {
//		Response actual = target("/login/00001").request().get();
//		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), actual.getStatus());
//	}

//	@Test(expected=WebApplicationException.class)
//	public void testGetUserByTokenNotRegistered() {
//		LoginService loginService = new LoginService();
//		UserName userName = Mockito.mock(UserName.class);
//		userName = loginService.getUserByToken("00001");
//		assertEquals(userName, null);
//	}

//	@Test
//	public void testCreateUser() {
//		LoginService loginService = new LoginService();
//		UserInformation user = Mockito.mock(UserInformation.class);
//		user.username = "Giorgi";
//		user.password = "blabla";
//		user.email = "giorgi@freeuni.edu.ge";
//		Response response = Mockito.mock(Response.class);
//		response = loginService.createUser(user);
//		assertEquals(response.getStatus(), Response.Status.CREATED.getStatusCode());
//	}
//
//	@Test
//	public void testCreateUserUsernameNull() {
//		LoginService loginService = new LoginService();
//		UserInformation user = Mockito.mock(UserInformation.class);
//		user.username = null;
//		user.password = "blabla";
//		user.email = "giorgi@freeuni.edu.ge";
//		Response response = Mockito.mock(Response.class);
//		response = loginService.createUser(user);
//		assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
//	}
//
//	@Test
//	public void testCreateUserPasswordNull() {
//		LoginService loginService = new LoginService();
//		UserInformation user = Mockito.mock(UserInformation.class);
//		user.username = "Giorgi";
//		user.password = null;
//		user.email = "giorgi@freeuni.edu.ge";
//		Response response = Mockito.mock(Response.class);
//		response = loginService.createUser(user);
//		assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
//	}
//
//	@Test
//	public void testCreateUserEmailNull() {
//		LoginService loginService = new LoginService();
//		UserInformation user = Mockito.mock(UserInformation.class);
//		user.username = "Giorgi";
//		user.password = "blabla";
//		user.email = null;
//		Response response = Mockito.mock(Response.class);
//		response = loginService.createUser(user);
//		assertEquals(response.getStatus(), Response.Status.BAD_REQUEST.getStatusCode());
//	}
//	
//	@Test
//	public void testCreateUser201Expected() {
//		UserInformation user = new UserInformation();
//		user.username = "Giorgi";
//		user.password = "blabla";
//		user.email = "giorgi@freeuni.edu.ge";
//        Response actual = target("/login/users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.CREATED.getStatusCode(), actual.getStatus());
//	}
//	
//	@Test
//	public void testCreateUserEmailNull404Expected() {
//		UserInformation user = new UserInformation();
//		user.username = "Giorgi";
//		user.password = "blabla";
//		user.email = null;
//        Response actual = target("/login/users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actual.getStatus());
//	}
//	
//	@Test
//	public void testCreateUserPasswordNull404Expected() {
//		UserInformation user = new UserInformation();
//		user.username = "Giorgi";
//		user.password = null;
//		user.email = "giorgi@freeuni.edu.ge";
//        Response actual = target("/login/users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actual.getStatus());
//	}
//	
//	@Test
//	public void testCreateUserNameNull404Expected() {
//		UserInformation user = new UserInformation();
//		user.username = null;
//		user.password = "blabla";
//		user.email = "giorgi@freeuni.edu.ge";
//        Response actual = target("/login/users").request().post(Entity.entity(user, MediaType.APPLICATION_JSON_TYPE));
//        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), actual.getStatus());
//	}
//	
//	@Test
//	public void testGetUserRegistered() {
//		LoginService loginService = new LoginService();
//		UserInformation userInfo = Mockito.mock(UserInformation.class);
//		userInfo = loginService.getUser("anna");
//		assertEquals(userInfo.username, "anna");
//		assertEquals(userInfo.password, "blabla");
//		assertEquals(userInfo.email, "agoro12@freeuni.edu.ge");
//	}
//
//	@Test(expected=WebApplicationException.class)
//	public void testGetUserNotRegistered() {
//		LoginService loginService = new LoginService();
//		UserInformation userInfo = Mockito.mock(UserInformation.class);
//		userInfo = loginService.getUser("Giorgi");
//		assertEquals(userInfo, null);
//	}
	
//	@Test
//	public void testGetUserRegistered200Expected(){
//		Response actual = target("/login/users/anna").request().get();
//		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
//	}
//	
//	@Test
//	public void testGetUserNotRegistered404Expected(){
//		Response actual = target("/login/users/giorgi").request().get();
//		assertEquals(Response.Status.NOT_FOUND.getStatusCode(), actual.getStatus());
//	}
	

}
