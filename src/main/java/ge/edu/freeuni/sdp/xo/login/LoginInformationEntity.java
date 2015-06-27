package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class LoginInformationEntity extends TableServiceEntity{
	
	public LoginInformationEntity(String username,String token){
		this.partitionKey = token;
		this.rowKey = username;
	}
	
	public LoginInformationEntity(){}
	
}