package hexaware.sc.autoinsurance.web.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;


public class ModelDto {
    
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("modelId")
    private long modelId;

    @JsonProperty("brandId")
    private long brandId;

    @JsonProperty("modelName")
    private String modelName;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("brand")
    private BrandDto brand;


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
        return brand;
    }
    public void setBrand(BrandDto brand) {
        this.brand = brand;
    }
    
    
    

    

}
