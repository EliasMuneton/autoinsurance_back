package hexaware.sc.autoinsurance.repositories;

import org.springframework.data.repository.CrudRepository;

import hexaware.sc.autoinsurance.domain.Brand;

public interface BrandRepository extends CrudRepository<Brand, Long>{
    
}
