package hexaware.sc.autoinsurance.web.model;


import com.fasterxml.jackson.annotation.JsonProperty;


public class ModelDto {
    
    
    @JsonProperty("modelId")
    private long modelId;

    @JsonProperty("brandId")
    private long brandId;

    @JsonProperty("modelName")
    private String modelName;

    @JsonProperty("Brand")
    private BrandDto Brand;


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
    public BrandDto getBrand() {
        return Brand;
    }
    public void setBrand(BrandDto brand) {
        Brand = brand;
    }
    
    

    

}
