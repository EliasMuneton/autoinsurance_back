package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.ModelDto;

public interface ModelService {
    
    Iterable<ModelDto> modelsByBrand(Long idBrand);

    Iterable<ModelDto> getAllModels();
}
