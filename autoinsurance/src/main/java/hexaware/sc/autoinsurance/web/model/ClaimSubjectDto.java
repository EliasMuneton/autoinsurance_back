package hexaware.sc.autoinsurance.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClaimSubjectDto {
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
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
