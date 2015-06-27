package ge.edu.freeuni.sdp.xo.login;

import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.Status;

import com.microsoft.azure.storage.StorageException;

@Path("/login")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class LoginService {

	private FakeKVStore getStore() {
		return new FakeKVStore();
	}
	
	private UsersCloud getUsersCloud() throws StorageException{
		return CloudFactory.create("xologinusers");
	}
	
	private TokensCloud getTokensCloud() throws StorageException{
		return CloudFactory.create1("xologintokens");
	}

	@PUT
	public Token loginUser(LoginInformation userInfo) throws StorageException{
		if(userInfo.password == null || userInfo.username == null){
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
		UserInformation currentUser = getUsersCloud().findUser(userInfo.username);
		if (currentUser == null || !currentUser.password.equals(userInfo.password)){
			throw new WebApplicationException(422);
		}
		final String uniqueToken = UUID.randomUUID().toString();
		Token newToken = new Token(uniqueToken);
		getTokensCloud().addOrUpdateUserToken(userInfo, newToken);
		return newToken;
	}

	@GET @Path("/{token}")
	public UserName getUserByToken(@PathParam("token") String token) throws StorageException{
		String username = getTokensCloud().findUser(token);
		if (username == null) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		UserName currentUser = new UserName();
		currentUser.username = username;
		return currentUser;
	}


	@POST @Path("/users")
	public Response createUser(UserInformation user) throws StorageException{
		if(user.email == null || user.password == null || user.username == null){
			return Response.status(Status.BAD_REQUEST).build();
		}
		boolean checker = getUsersCloud().addUser(user);
		if(!checker){
			return Response.status(Status.CONFLICT).build();
		}
		return Response.status(Status.CREATED).build();
	}


	@GET @Path("/users/{username}")
	public UserInformation getUser(@PathParam("username") String userName) throws StorageException{
		UserInformation currentUser = getUsersCloud().findUser(userName);
		if (currentUser == null){
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		return currentUser;
	}

	// this method was needed for fake implementation
	private boolean correctCredentials(LoginInformation loginInfo) {
		UserInformation userInfo = getStore().getByName(loginInfo.username);
		if (userInfo == null) {
			return false;
		}
		return userInfo.password.equals(loginInfo.password);
	}

	// this method was needed for fake implementation
	private void setToken(LoginInformation loginInfo, Token token) {
		UserInformation userInfo = getStore().getByName(loginInfo.username);
		getStore().putByToken(token.token, userInfo);
	}

}
