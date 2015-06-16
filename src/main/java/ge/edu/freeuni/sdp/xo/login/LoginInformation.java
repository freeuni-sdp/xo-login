package ge.edu.freeuni.sdp.xo.login;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class LoginInformation {

	@XmlElement
    public String username;
    
    @XmlElement
    public String password;
    
    @Override
    public String toString(){
    	return username + ", " + password;
    }
  
}
