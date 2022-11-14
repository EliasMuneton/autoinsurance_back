package hexaware.sc.autoinsurance.repositories;

import org.springframework.data.repository.CrudRepository;

import hexaware.sc.autoinsurance.domain.Vehicle;

public interface VehicleRepository  extends CrudRepository<Vehicle, Long>{
    

}