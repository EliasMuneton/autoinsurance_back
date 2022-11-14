package hexaware.sc.autoinsurance.web.model;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ClaimDto {
    
    
    private long claimId;
    
    @JsonProperty("claimSubjectId")
    @NotNull
    private long claimSubjectId;

    @JsonProperty("userId")
    private long userId;
    
    @JsonProperty("claimStatusId")
    @NotNull
    private long claimStatusId;
    
    @JsonProperty("description")
    @NotNull
    private String description;

    

    @JsonProperty("claimSubject")
    private ClaimSubjectDto claimSubject;
    
    @JsonProperty("claimStatus")
    private ClaimStatusDto claimStatus;

    @JsonProperty("user")
    private UserDto user;

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public long getClaimSubjectId() {
        return claimSubjectId;
    }

    public void setClaimSubjectId(long claimSubjectId) {
        this.claimSubjectId = claimSubjectId;
    }

    public long getClaimStatusId() {
        return claimStatusId;
    }

    public void setClaimStatusId(long claimStatusId) {
        this.claimStatusId = claimStatusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ClaimSubjectDto getClaimSubject() {
        return claimSubject;
    }

    public void setClaimSubject(ClaimSubjectDto claimSubject) {
        this.claimSubject = claimSubject;
    }

    public ClaimStatusDto getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatusDto claimStatus) {
        this.claimStatus = claimStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    

    
    
}