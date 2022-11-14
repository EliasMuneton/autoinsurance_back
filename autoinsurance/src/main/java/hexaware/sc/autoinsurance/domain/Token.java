package hexaware.sc.autoinsurance.domain;

public class Token {
    
    private Long userId;

    private Long userRolId;

    private String email;

    

    public Token(Long userId, Long userRolId, String email) {
        this.userId = userId;
        this.userRolId = userRolId;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserRolId() {
        return userRolId;
    }

    public void setUserRolId(Long userRolId) {
        this.userRolId = userRolId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
