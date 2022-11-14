package hexaware.sc.autoinsurance.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;


@Entity
@Table(name = "claim_subjects")
@Where(clause = " deleted_by is null and deleted_at is null")
public class ClaimSubject {
    
    @Id
    @Column(name = "claim_subject_id", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long claimSubjectId;

    @Column(name = "description")
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