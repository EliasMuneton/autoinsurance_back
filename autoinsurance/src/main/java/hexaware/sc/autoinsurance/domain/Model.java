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
@Table(name = "models")
@Where(clause = " deleted_by is null and deleted_at is null")
public class Model {
    
    @Id
    @Column(name = "model_id", columnDefinition = "serial")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long modelId;
    
    @Column(name="brand_id")
    private long brandId;

    @Column(name = "model_name")
    private String modelName;

    @JoinColumn(name = "brand_id", insertable=false, updatable=false, nullable=false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Brand brand;

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

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
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

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    
    
    
    
}
