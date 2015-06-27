package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class UserInformationEntity extends TableServiceEntity{

	private String password;
	
	
	public UserInformationEntity(String email,String username){
		this.partitionKey = email;
		this.rowKey = username;
	}
	
	public UserInformationEntity(){}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
	
}
