package hexaware.sc.autoinsurance.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity
@Table(name = "claims")
@Where(clause = " deleted_by is null and deleted_at is null")
public class Claim {
    
    @Id
    @Column(name = "claim_id", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long claimId;
    
    
    @Column(name="claim_subject_id")
    private long claimSubjectId;
    
    @Column(name="claim_status_id")
    private long claimStatusId;

    @Column(name = "vehicle_id")
    private long vehicleId;

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

    @JoinColumn(name = "claim_subject_id", insertable=false, updatable=false, nullable=false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClaimSubject claimSubject;

    @JoinColumn(name = "claim_status_id", insertable=false, updatable=false, nullable=false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ClaimStatus claimStatus;

    @JoinColumn(name = "vehicle_id", insertable=false, updatable=false, nullable=false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Vehicle vehicle;

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

    public ClaimSubject getClaimSubject() {
        return claimSubject;
    }

    public void setClaimSubject(ClaimSubject claimSubject) {
        this.claimSubject = claimSubject;
    }

    public ClaimStatus getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(ClaimStatus claimStatus) {
        this.claimStatus = claimStatus;
    }


    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    
           
}