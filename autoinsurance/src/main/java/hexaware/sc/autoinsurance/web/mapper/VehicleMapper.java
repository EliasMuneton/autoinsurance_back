package hexaware.sc.autoinsurance.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.Vehicle;
import hexaware.sc.autoinsurance.web.model.VehicleDto;

@Mapper (
    componentModel = "spring"
)
public interface VehicleMapper {
    
    VehicleDto vehcileToVehicleDto(Vehicle vehicle);

    Vehicle vehicleDtoToVehicle(VehicleDto vehicleDto);

    Iterable<VehicleDto> vechilesToVehiclesDto(List<Vehicle> vehicles);
}
