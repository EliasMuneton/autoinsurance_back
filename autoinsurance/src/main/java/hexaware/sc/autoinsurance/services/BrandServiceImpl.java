package hexaware.sc.autoinsurance.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hexaware.sc.autoinsurance.repositories.BrandRepository;
import hexaware.sc.autoinsurance.web.mapper.BrandMapper;
import hexaware.sc.autoinsurance.web.model.BrandDto;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandMapper brandMapper;
    private BrandRepository brandRepository;

    @Autowired
    public void setBrandMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
    }

    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public Iterable<BrandDto> getAllBrands() {
        return brandMapper.bransToBrandsDtos(brandRepository.findAll());
    }
    
}
