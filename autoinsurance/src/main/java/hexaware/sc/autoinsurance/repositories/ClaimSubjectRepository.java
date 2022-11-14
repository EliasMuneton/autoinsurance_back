package hexaware.sc.autoinsurance.repositories;

import org.springframework.data.repository.CrudRepository;

import hexaware.sc.autoinsurance.domain.ClaimSubject;

public interface ClaimSubjectRepository extends CrudRepository<ClaimSubject, Long>{
    
}
