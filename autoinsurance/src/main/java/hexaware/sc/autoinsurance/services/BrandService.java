package hexaware.sc.autoinsurance.services;


import hexaware.sc.autoinsurance.web.model.BrandDto;

public interface BrandService {
    
    Iterable<BrandDto> getAllBrands();
}
