

import static org.junit.Assert.*;
import ge.edu.freeuni.sdp.xo.login.Token;

import org.junit.Test;

public class TokenTest {

	@Test
	public void test() {
		Token tokenSandro = new Token();
		tokenSandro.token = "00000";
		assertEquals(tokenSandro.token, "00000");
	}

}
