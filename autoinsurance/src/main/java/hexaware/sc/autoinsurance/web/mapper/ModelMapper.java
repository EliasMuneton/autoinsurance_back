package hexaware.sc.autoinsurance.web.mapper;

import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.Model;
import hexaware.sc.autoinsurance.web.model.ModelDto;

@Mapper(
    componentModel = "spring"
)
public interface ModelMapper {
    
    Model modelDtoToMaodel(ModelDto model);

    ModelDto modelToModelDto(Model model);

    Iterable<ModelDto> modelsToModelsDtos(Iterable<Model> models);
}
