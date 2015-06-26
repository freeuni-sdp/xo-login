package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;

public class UsersCloud {

	private CloudTable table;
	
	public UsersCloud(CloudTable table){
		this.table = table;
	}
	
	public void addUser(UserInformation userInfo){
		UserInformationEntity user = new UserInformationEntity(userInfo.username, userInfo.password);
		user.setEmail(userInfo.email);
		TableOperation insertUser = TableOperation.insertOrReplace(user);
		try {
			table.execute(insertUser);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
	public void findUser(UserName userName){
		
	}
	
}
