package hexaware.sc.autoinsurance.web.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class LoginDto {
    

    @NotBlank
    @NotNull
    @JsonProperty("email")
    private String email;
    

    @NotBlank
    @NotNull
    @JsonProperty("pass")
    private String pass;


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPass() {
        return pass;
    }


    public void setPass(String pass) {
        this.pass = pass;
    }


    
}
