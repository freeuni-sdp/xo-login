package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.table.TableServiceEntity;

public class UserInformationEntity extends TableServiceEntity{

	String email;
	
	public UserInformationEntity(String username,String password){
		this.partitionKey = username;
		this.rowKey = password;
	}
	
	
	public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
}
