package ge.edu.freeuni.sdp.xo.login;

import java.util.HashMap;

class FakeKVStore{

    private static HashMap<String, UserInformation> users = new HashMap<>();  		// username -> data
    private static HashMap<String, UserInformation> tokenCheck = new HashMap<>(); 	// token -> data

    static {
        UserInformation newUser = new UserInformation();
        newUser.email = "slezh12@freeuni.edu.ge";
        newUser.password = "blabla";
        newUser.username = "sandro";

        UserInformation newUser1 = new UserInformation();
        newUser1.email = "agoro12@freeuni.edu.ge";
        newUser1.password = "blabla";
        newUser1.username = "anna";

        users.put(newUser.username, newUser);
        users.put(newUser1.username, newUser1);
        tokenCheck.put("00000", newUser);
        // newUser1 without token
    }

    public UserInformation getByName(String k1) {
        return users.get(k1);
    }

    public UserInformation getByToken(String k2) {
        return tokenCheck.get(k2);
    }

    public void putByToken(String k2, UserInformation userInfo) {
        tokenCheck.put(k2, userInfo);
    }
}
