package ge.edu.freeuni.sdp.xo.login;

public class UserToken {
	private String token;
	private String username;
	
	public UserToken(String token, String username){
		this.token = token;
		this.username = username;
	}
	
	public String getToken(){ return token; }
	
	public String getUserName(){ return username; }
	
}
