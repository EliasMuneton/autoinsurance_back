package hexaware.sc.autoinsurance.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorDto {
    
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
