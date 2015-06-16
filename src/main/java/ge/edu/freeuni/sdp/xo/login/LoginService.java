package ge.edu.freeuni.sdp.xo.login;


import java.util.HashMap;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class LoginService {

	
	static HashMap<String, UserInformation> users = new HashMap<>();
	static HashMap<String,UserName> tokenCheck = new HashMap<>();
	static HashMap<LoginInformation,Token> loginCheck = new HashMap<>(); 
	
	{
		UserInformation newUser = new UserInformation();
		newUser.email = "slezh12@freeuni.edu.ge";
		newUser.password = "blabla";
		newUser.username = "sandro";
		users.put("sandro", newUser);
		
		UserInformation newUser1 = new UserInformation();
		newUser1.email = "agoro12@freeuni.edu.ge";
		newUser1.password = "blabla";
		newUser1.username = "anna";
		users.put("anna", newUser1);
		
		UserName sandro = new UserName();
		sandro.username = "sandro";
		tokenCheck.put("00000",sandro);
		
		UserName anna = new UserName();
		anna.username = "anna";
		tokenCheck.put("11111", anna);
		
		LoginInformation loginSandro = new LoginInformation();
		loginSandro.username = "sandro";
		loginSandro.password = "blabla";
		LoginInformation loginAnna = new LoginInformation();
		loginAnna.password = "blabla";
		loginAnna.username = "anna";
		Token tokenSandro = new Token();
		tokenSandro.token = "00000";
		Token tokenAnna = new Token();
		tokenAnna.token = "11111";
		
		loginCheck.put(loginSandro, tokenSandro);
		loginCheck.put(loginAnna, tokenAnna);
		
	}
	
	
	@PUT
	@Path("/")
	public Token loginUser(LoginInformation userInfo){
		if(userInfo.password == null || userInfo.username == null){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		Token currentToken = loginCheck.get(userInfo);
		if(currentToken == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return currentToken;
	}
	
	@GET
	@Path("/{token}")
	public UserName getUserByToken(@PathParam("token") String token){ 
		UserName currentUser = tokenCheck.get(token);
		if (currentUser == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return currentUser;
	}
	
	
	@POST
	@Path("/users")
	public Response createUser(UserInformation user){
		if(user.email == null || user.password == null || user.username == null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.CREATED).build();
	}
	
	
	@GET
	@Path("/users/{username}")
	public UserInformation getUser(@PathParam("username") String userName){
		UserInformation currentUser = users.get(userName);
		if (currentUser == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return currentUser;
	}
	
}
