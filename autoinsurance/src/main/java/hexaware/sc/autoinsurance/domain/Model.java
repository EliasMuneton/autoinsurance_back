package hexaware.sc.autoinsurance.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
    private long modelId;
    
    @Column(name="brand_id")
    private long brandId;

    @Column(name = "model_name")
    private String modelName;

    @JoinColumn(name = "brand_id", insertable=false, updatable=false, nullable=false)
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Brand Brand;

    

    public Model() {
    }

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

    public Brand getBrand() {
        return Brand;
    }

    public void setBrand(Brand brand) {
        Brand = brand;
    }

    
    
    
    
}
