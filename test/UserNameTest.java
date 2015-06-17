

import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.login.UserName;

import org.junit.Test;

public class UserNameTest {

	@Test
	public void testUsername() {
		UserName sandro = new UserName();
		sandro.username = "sandro";
		assertEquals("sandro", sandro.username);
	}
}
