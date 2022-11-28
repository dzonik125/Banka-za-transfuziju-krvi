package group8.bloodbank.model.DTO;

import lombok.Getter;
import lombok.Setter;

// DTO za login
@Getter
@Setter
public class JwtAuthenticationRequest {
	
    private String username;
    private String password;

    public JwtAuthenticationRequest() {
        super();
    }

    public JwtAuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

}
