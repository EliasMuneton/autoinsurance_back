package hexaware.sc.autoinsurance.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClaimSubjectDto {
    
    @JsonProperty("claimSubjectId")
    private long claimSubjectId;
    
    @JsonProperty("description")
    private String description;

    public long getClaimSubjectId() {
        return claimSubjectId;
    }

    public void setClaimSubjectId(long claimSubjectId) {
        this.claimSubjectId = claimSubjectId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    
}
