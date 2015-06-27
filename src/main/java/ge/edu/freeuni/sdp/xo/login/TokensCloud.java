package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

public class TokensCloud {

	private CloudTable table;
	final String PARTITION_KEY = "PartitionKey";
	
	public TokensCloud(CloudTable table){
		this.table = table;
	}
	
	public boolean addOrUpdateUserToken(LoginInformation loginInfo, Token token){
		LoginInformationEntity login = new LoginInformationEntity(loginInfo.username, token.token);
		TableOperation insertUser = TableOperation.insertOrReplace(login);
		try {
			table.execute(insertUser);
		} catch (StorageException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public String findUser(String token){
		String partitionKeyFilter = TableQuery.generateFilterCondition(PARTITION_KEY, QueryComparisons.EQUAL, token);
		TableQuery<LoginInformationEntity> partitionQuery =
			       TableQuery.from(LoginInformationEntity.class)
			       .where(partitionKeyFilter);
		for (LoginInformationEntity entity : table.execute(partitionQuery)) {
			String username = entity.getRowKey();
			return username;
	    } 
		return null;	
	}
	
}
