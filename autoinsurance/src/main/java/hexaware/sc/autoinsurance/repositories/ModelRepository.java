package hexaware.sc.autoinsurance.repositories;

import org.springframework.data.repository.CrudRepository;

import hexaware.sc.autoinsurance.domain.Model;

public interface ModelRepository extends CrudRepository<Model, Long>{
 
    Iterable<Model> findAllByBrandId(Long brandId);
}
