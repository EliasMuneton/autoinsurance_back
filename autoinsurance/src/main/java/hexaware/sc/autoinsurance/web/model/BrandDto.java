package hexaware.sc.autoinsurance.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class BrandDto {
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("brandId")
    private long brandId;

    @JsonProperty("brandName")
    private String brandName;

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    
}
