package hexaware.sc.autoinsurance.services;


import hexaware.sc.autoinsurance.web.model.BrandDto;

public interface BrandService {
    
    Iterable<BrandDto> getAllBrands();

    BrandDto saveNewBrand(BrandDto brandDto) throws Exception;

    BrandDto updateBrand(BrandDto brandDto, long id) throws Exception;

}
