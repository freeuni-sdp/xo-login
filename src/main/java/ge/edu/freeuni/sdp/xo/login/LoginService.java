package ge.edu.freeuni.sdp.xo.login;

import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

@Path("/login")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class LoginService {

	private FakeKVStore getStore() {
		return new FakeKVStore();
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
