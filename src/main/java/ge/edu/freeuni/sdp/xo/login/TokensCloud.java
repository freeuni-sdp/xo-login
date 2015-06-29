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
	
	public boolean addOrUpdateUserToken(LoginInformation loginInfo, Token token){
		UserToken userToken = findUserByUsername(loginInfo.username);
		if(userToken != null){
			System.out.println("token: " + userToken.getToken());
			LoginInformationEntity login = new LoginInformationEntity(userToken.getUserName(), userToken.getToken());
			login.setEtag("*");
			TableOperation deleteUser = TableOperation.delete(login);
			try {
				table.execute(deleteUser);
			} catch (StorageException e) {
				e.printStackTrace();
			}
		}
		System.out.println("new token: " + token.token);
		LoginInformationEntity login = new LoginInformationEntity(loginInfo.username, token.token);
		TableOperation insertUser = TableOperation.insertOrReplace(login);
		try {
			table.execute(insertUser);
		} catch (StorageException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public UserToken findUserByUsername(String username){
		String partitionKeyFilter = TableQuery.generateFilterCondition(PARTITION_KEY, QueryComparisons.EQUAL, username);
		TableQuery<LoginInformationEntity> partitionQuery =
			       TableQuery.from(LoginInformationEntity.class)
			       .where(partitionKeyFilter);
		for (LoginInformationEntity entity : table.execute(partitionQuery)) {
			UserToken userToken = new UserToken(entity.getRowKey(), entity.getPartitionKey());
			return userToken;
	    } 
		return null;	
	}
	
	public String findUser(String token){
		String rowKeyFilter = TableQuery.generateFilterCondition(ROW_KEY, QueryComparisons.EQUAL, token);
		TableQuery<LoginInformationEntity> rowQuery =
			       TableQuery.from(LoginInformationEntity.class)
			       .where(rowKeyFilter);
		for (LoginInformationEntity entity : table.execute(rowQuery)) {
			return entity.getPartitionKey();
	    } 
		return null;		
	}
	
}
