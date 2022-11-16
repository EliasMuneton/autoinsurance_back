package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.ModelDto;

public interface ModelService {
    
    Iterable<ModelDto> modelsByBrand(Long idBrand);

    Iterable<ModelDto> getAllModels();

    ModelDto saveNewModel(ModelDto modelDto) throws Exception;

    ModelDto updateModel(ModelDto modelDto, long id) throws Exception;
}
