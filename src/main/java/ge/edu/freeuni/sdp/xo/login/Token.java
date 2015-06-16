package ge.edu.freeuni.sdp.xo.login;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Token {

	@XmlElement
    public String token;
	
}
