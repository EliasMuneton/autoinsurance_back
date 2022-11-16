package hexaware.sc.autoinsurance.web.model;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;



public class VehicleDto {
    
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("vehicleId")
    private long vehicleId;
    
    @JsonProperty("userId")
    @NotNull
    private long userId;
    
    @JsonProperty("modelId")
    @NotNull
    private long modelId;
    
    @JsonProperty("colorId")
    @NotNull
    private long colorId;
    
    @JsonProperty("vehicleYear")
    private Integer vehicleYear;
    
    @JsonProperty("licencePlate")
    @NotNull
    private String licencePlate;
    
    @JsonProperty("description")
    private String description;
    
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("model")
    private ModelDto model;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("color")
    private ColorDto color;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @JsonProperty("user")
    private UserDto user;

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public long getColorId() {
        return colorId;
    }

    public void setColorId(long colorId) {
        this.colorId = colorId;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public ColorDto getColor() {
        return color;
    }

    public void setColor(ColorDto color) {
        this.color = color;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    

    
}
