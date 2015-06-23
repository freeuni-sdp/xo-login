package ge.edu.freeuni.sdp.xo.login;


import java.util.HashMap;
import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Path("/login")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class LoginService {

	private static class KVStore{
		private KVStore kvStore;

		private static HashMap<String, UserInformation> users = new HashMap<>();  		// username -> data
		private static HashMap<String, UserInformation> tokenCheck = new HashMap<>(); 	// token -> data

		static {
			UserInformation newUser = new UserInformation();
			newUser.email = "slezh12@freeuni.edu.ge";
			newUser.password = "blabla";
			newUser.username = "sandro";

			UserInformation newUser1 = new UserInformation();
			newUser1.email = "agoro12@freeuni.edu.ge";
			newUser1.password = "blabla";
			newUser1.username = "anna";

			users.put(newUser.username, newUser);
			users.put(newUser1.username, newUser1);
			tokenCheck.put("some_token", newUser);
			// newUser1 without token
		}

		public UserInformation getByName(String k1) {
			return users.get(k1);
		}

		public UserInformation getByToken(String k2) {
			return tokenCheck.get(k2);
		}

		public void putByToken(String k2, UserInformation userInfo) {
			tokenCheck.put(k2, userInfo);
		}
	}

	private KVStore getStore() {
		return new KVStore();
	}


	@PUT
	public Token loginUser(LoginInformation userInfo){
		if(userInfo.password == null || userInfo.username == null){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

		if (!correctCredentials(userInfo)) {
			throw new WebApplicationException(422);
		}
		final String uniqueToken = UUID.randomUUID().toString();
		Token newToken = new Token(uniqueToken);
		setToken(userInfo, newToken);

		return newToken;
	}

	@GET @Path("/{token}")
	public UserName getUserByToken(@PathParam("token") String token){
		UserInformation userInfo = getStore().getByToken(token);
		if (userInfo == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		UserName currentUser = new UserName();
		currentUser.username = userInfo.username;
		return currentUser;
	}


	@POST @Path("/users")
	public Response createUser(UserInformation user){
		if(user.email == null || user.password == null || user.username == null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.CREATED).build();
	}


	@GET @Path("/users/{username}")
	public UserInformation getUser(@PathParam("username") String userName){
		UserInformation currentUser = getStore().getByName(userName);
		if (currentUser == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return currentUser;
	}

	private boolean correctCredentials(LoginInformation loginInfo) {
		UserInformation userInfo = getStore().getByName(loginInfo.username);
		if (userInfo == null) {
			return false;
		}
		return userInfo.password.equals(loginInfo.password);
	}

	private void setToken(LoginInformation loginInfo, Token token) {
		UserInformation userInfo = getStore().getByName(loginInfo.username);
		getStore().putByToken(token.token, userInfo);
	}

}
