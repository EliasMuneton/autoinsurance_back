package hexaware.sc.autoinsurance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hexaware.sc.autoinsurance.repositories.ModelRepository;
import hexaware.sc.autoinsurance.web.mapper.ModelMapper;
import hexaware.sc.autoinsurance.web.model.ModelDto;

@Service
public class ModelServiceImpl implements ModelService {

    private ModelMapper modelMapper;
    private ModelRepository modelRepository;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public Iterable<ModelDto> modelsByBrand(Long idBrand) {
        return modelMapper.modelsToModelsDtos(modelRepository.findAllByBrandId(idBrand));
    }

    @Override
    public Iterable<ModelDto> getAllModels() {
        return modelMapper.modelsToModelsDtos(modelRepository.findAll());
    }
    
}
