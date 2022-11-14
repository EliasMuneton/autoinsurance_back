package hexaware.sc.autoinsurance.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClaimStatusDto {
    
    @JsonProperty("claimStatusId")
    private long claimStatusId;
    
    @JsonProperty("description")
    private String description;

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

    
}
