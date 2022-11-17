package hexaware.sc.autoinsurance.repositories;


import org.springframework.data.repository.CrudRepository;

import hexaware.sc.autoinsurance.domain.Claim;

public interface ClaimRepository  extends CrudRepository<Claim, Long>{
    
    Iterable<Claim> findAllByVehicleId(Long id);
}