package hexaware.sc.autoinsurance.domain;


import java.util.Date;

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


    @Column(name = "created_at")
    private Date createdAt;
	
    @Column(name = "created_by")
    private Long createdBy;
	
    @Column(name = "updated_at")
    private Date updatedAt;
	
    @Column(name = "updated_by")
    private Long updatedBy;
	
    @Column(name = "deleted_at")
    private Date deletedAt;
	
    @Column(name = "deleted_by")
    private Long deletedBy;
    
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Long getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Long deletedBy) {
        this.deletedBy = deletedBy;
    }

    
    
}