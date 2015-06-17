

import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.login.LoginInformation;

import org.junit.Test;

public class LoginInformationTest {

	@Test
	public void testToString() {
		LoginInformation loginAnna = new LoginInformation();
		loginAnna.password = "blabla";
		loginAnna.username = "anna";
		assertEquals(loginAnna.toString(), "anna, blabla");
	}

}
