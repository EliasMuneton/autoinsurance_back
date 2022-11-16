package hexaware.sc.autoinsurance.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hexaware.sc.autoinsurance.domain.Brand;
import hexaware.sc.autoinsurance.domain.Model;
import hexaware.sc.autoinsurance.domain.Token;
import hexaware.sc.autoinsurance.repositories.BrandRepository;
import hexaware.sc.autoinsurance.repositories.ModelRepository;
import hexaware.sc.autoinsurance.security.JWTUtil;
import hexaware.sc.autoinsurance.web.mapper.ModelMapper;
import hexaware.sc.autoinsurance.web.model.ModelDto;

@Service
public class ModelServiceImpl implements ModelService {

    private ModelMapper modelMapper;
    private ModelRepository modelRepository;
    private BrandRepository brandRepository;
    private JWTUtil jwtUtil;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setModelRepository(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Autowired
    public void setTokenUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Iterable<ModelDto> modelsByBrand(Long idBrand) {
        return modelMapper.modelsToModelsDtos(modelRepository.findAllByBrandId(idBrand));
    }

    @Override
    public Iterable<ModelDto> getAllModels() {
        return modelMapper.modelsToModelsDtos(modelRepository.findAll());
    }

    @Override
    public ModelDto saveNewModel(ModelDto modelDto) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1) { // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        }
        Model model = modelMapper.modelDtoToMaodel(modelDto);
        model = validBrand(model);
        model.setCreatedAt(new Date());
        model.setCreatedBy(tokenData.getUserId());
        return modelMapper.modelToModelDto(modelRepository.save(model));
    }

    @Override
    public ModelDto updateModel(ModelDto modelDto, long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1)  // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        Optional<Model> modelExist =  modelRepository.findById(id);
        if (!modelExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        Model model = modelExist.get();
        model.setBrandId(modelDto.getBrandId());
        model.setModelName(modelDto.getModelName());
        model = validBrand(model);
        model.setUpdatedAt(new Date());
        model.setUpdatedBy(tokenData.getUserId());
        return modelMapper.modelToModelDto(modelRepository.save(model));
    }
    
    public Model validBrand(Model model) {
        Optional<Brand> existBrand = brandRepository.findById(model.getBrandId());
        if (!existBrand.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID Brand " + model.getBrandId() + " not found");
        model.setBrand(existBrand.get());
        return model;
    }
}
