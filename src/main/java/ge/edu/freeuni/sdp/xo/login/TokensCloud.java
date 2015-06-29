package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class TokensCloud {

	private CloudTable table;
	final String ROW_KEY = "RowKey";
	final String PARTITION_KEY = "PartitionKey";
	
	public TokensCloud(CloudTable table){
		this.table = table;
	}
	
	public void addOrUpdateUserToken(LoginInformation loginInfo, Token token){
		LoginInformationEntity login = new LoginInformationEntity(loginInfo.username, loginInfo.password);
		login.setToken(token.token);
		//System.out.println("mdaa====" + login.getToken());
		TableOperation insertUser = TableOperation.insertOrReplace(login);
		//findUser(loginInfo.username);
		try {
			table.execute(insertUser);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
//	public UserToken findUserByUsername(String username){
//		String partitionKeyFilter = TableQuery.generateFilterCondition(PARTITION_KEY, QueryComparisons.EQUAL, username);
//		TableQuery<LoginInformationEntity> partitionQuery =
//			       TableQuery.from(LoginInformationEntity.class)
//			       .where(partitionKeyFilter);
//		for (LoginInformationEntity entity : table.execute(partitionQuery)) {
//			UserToken userToken = new UserToken(entity.getToken(), entity.getRowKey());
//			return userToken;
//	    } 
//		return null;	
//	}
	
	public String findUser(String token){
		TableQuery<LoginInformationEntity> query = TableQuery.from(LoginInformationEntity.class);
		for (LoginInformationEntity entity : table.execute(query)) {
	        if(entity.getToken().equals(token)){
	        	String username = entity.getRowKey();
	        	return username;
	        }
			
		}
		return null;	
	}
	
}
