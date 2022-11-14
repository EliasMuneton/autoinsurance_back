package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.VehicleDto;

public interface VehicleService {
    
    VehicleDto getVehicleById(long id) throws Exception;

    VehicleDto saveNewVehicle(VehicleDto vehicle) throws Exception;

    VehicleDto updateVehicle(VehicleDto vehicle, long id) throws Exception;

    boolean deleteVehicle(long id) throws Exception;

    Iterable<VehicleDto> searchVehicle(VehicleDto vehicle);

}
