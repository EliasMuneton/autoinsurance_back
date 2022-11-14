package hexaware.sc.autoinsurance.web.mapper;


import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.Brand;
import hexaware.sc.autoinsurance.web.model.BrandDto;

@Mapper(
    componentModel = "spring"
)
public interface BrandMapper {
    
    Brand brandDtoToBrand(BrandDto brandDto);

    BrandDto brandToBrandDto(Brand brand);

    Iterable<BrandDto> bransToBrandsDtos(Iterable<Brand> barnds);
}
