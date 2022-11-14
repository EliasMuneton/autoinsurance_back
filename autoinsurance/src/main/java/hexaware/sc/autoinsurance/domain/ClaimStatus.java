package hexaware.sc.autoinsurance.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "claim_status")
@Where(clause = " deleted_by is null and deleted_at is null")
public class ClaimStatus {

    @Id
    @Column(name = "claim_status_id")
    private long claimStatusId;

    @Column(name = "description")
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
