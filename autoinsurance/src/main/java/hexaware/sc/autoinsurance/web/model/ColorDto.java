package hexaware.sc.autoinsurance.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

public class ColorDto {
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("colorId")
    private long colorId;

    @JsonProperty("colorName")
    private String colorName;
    public long getColorId() {
        return colorId;
    }
    public void setColorId(long colorId) {
        this.colorId = colorId;
    }
    public String getColorName() {
        return colorName;
    }
    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    
}   
