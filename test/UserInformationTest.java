

import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.login.UserInformation;

import org.junit.Test;

public class UserInformationTest {

	@Test
	public void testToString() {
		UserInformation newUser = new UserInformation();
		newUser.email = "slezh12@freeuni.edu.ge";
		newUser.password = "blabla";
		newUser.username = "sandro";
		assertEquals(newUser.toString(), "sandro, blabla, slezh12@freeuni.edu.ge");
	}

}
