package hexaware.sc.autoinsurance.repositories;

import org.springframework.data.repository.CrudRepository;

import hexaware.sc.autoinsurance.domain.ClaimStatus;

public interface ClaimStatusRepository extends CrudRepository<ClaimStatus, Long> {
    
}
