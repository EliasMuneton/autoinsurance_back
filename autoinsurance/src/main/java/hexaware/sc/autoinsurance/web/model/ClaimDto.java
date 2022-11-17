package hexaware.sc.autoinsurance.web.model;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


public class ClaimDto {
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("claimId")
    private long claimId;
    

    @JsonProperty("claimSubjectId")
    @NotNull
    private long claimSubjectId;

    @JsonProperty("userId")
    private long userId;

    @JsonProperty("vehicleId")
    private long vehicleId;
    
    @JsonProperty("claimStatusId")
    @NotNull
    private long claimStatusId;
    
    @JsonProperty("description")
    @NotNull
    private String description;

    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("claimSubject")
    private ClaimSubjectDto claimSubject;
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("claimStatus")
    private ClaimStatusDto claimStatus;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("user")
    private UserDto user;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("vehicle")
    private VehicleDto vehicle;

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

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public VehicleDto getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDto vehicle) {
        this.vehicle = vehicle;
    }

    

    
    
}
