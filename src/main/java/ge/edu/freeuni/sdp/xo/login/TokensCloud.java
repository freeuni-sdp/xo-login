package ge.edu.freeuni.sdp.xo.login;

import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;

public class TokensCloud {

	private CloudTable table;
	
	public TokensCloud(CloudTable table){
		this.table = table;
	}
	
	public void addOrUpdateUserToken(LoginInformation loginInfo, Token token){
		LoginInformationEntity login = new LoginInformationEntity(loginInfo.username, loginInfo.password);
		login.setToken(token.token);
		TableOperation insertUser = TableOperation.insertOrReplace(login);
		try {
			table.execute(insertUser);
		} catch (StorageException e) {
			e.printStackTrace();
		}
	}
	
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
