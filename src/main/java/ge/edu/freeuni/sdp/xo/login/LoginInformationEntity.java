package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class LoginInformationEntity extends TableServiceEntity{
	
	private String token;
	
	
	public LoginInformationEntity(String username, String password){
		this.partitionKey = password;
		this.rowKey = username;
	}
	
	public LoginInformationEntity(){}
	
	public void setToken(String token){
		this.token = token;
	}
	
	public String getToken(){
		return this.token;
	}
	
}