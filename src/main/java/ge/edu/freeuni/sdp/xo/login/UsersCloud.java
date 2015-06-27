package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class UsersCloud {

	private CloudTable table;
	final String ROW_KEY = "RowKey";
	
	public UsersCloud(CloudTable table){
		this.table = table;
	}
	
	public boolean addUser(UserInformation userInfo){
		if(findUser(userInfo.username) != null){
			return false;
		}
		UserInformationEntity user = new UserInformationEntity(userInfo.email, userInfo.username);
		user.setPassword(userInfo.password);
		TableOperation insertUser = TableOperation.insertOrReplace(user);
		try {
			table.execute(insertUser);
		} catch (StorageException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public UserInformation findUser(String userName){
		String rowKeyFilter = TableQuery.generateFilterCondition(ROW_KEY, QueryComparisons.EQUAL, userName);
		TableQuery<UserInformationEntity> rowQuery =
			       TableQuery.from(UserInformationEntity.class)
			       .where(rowKeyFilter);
		for (UserInformationEntity entity : table.execute(rowQuery)) {
			UserInformation userInfo = new UserInformation();
			userInfo.email = entity.getPartitionKey();
			userInfo.password = entity.getPassword();
			userInfo.username = entity.getRowKey();
			return userInfo;
	    } 
		return null;	
	}
	
}
