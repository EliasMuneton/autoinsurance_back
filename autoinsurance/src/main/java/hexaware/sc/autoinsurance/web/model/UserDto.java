package hexaware.sc.autoinsurance.web.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class UserDto {
    
    @JsonIgnore
    private long userId;

    @JsonProperty("userRoleId")
    private long userRoleId;

    @NotBlank
    @Email
    @NotNull
    @JsonProperty("email")
    private String email;
    
    
    @NotBlank
    @NotNull
    @JsonProperty("pass")
    private String pass;
    
    @NotBlank
    @NotNull
    @JsonProperty("firstName")
    private String firstName;
    
    @JsonProperty("lastName")
    private String lastName;
    
    @JsonProperty("userStatusId")
    private long userStatusId;
    
    @JsonProperty("birthdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING, timezone = "America/Mexico_City")
    private Date birthdate;

    

    
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(long userRoleId) {
        this.userRoleId = userRoleId;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getUserStatusId() {
        return userStatusId;
    }

    public void setUserStatusId(long userStatusId) {
        this.userStatusId = userStatusId;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

   
    

        
}
