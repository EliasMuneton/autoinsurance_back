package hexaware.sc.autoinsurance.services;


import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hexaware.sc.autoinsurance.domain.Brand;
import hexaware.sc.autoinsurance.domain.Token;
import hexaware.sc.autoinsurance.repositories.BrandRepository;
import hexaware.sc.autoinsurance.security.JWTUtil;
import hexaware.sc.autoinsurance.web.mapper.BrandMapper;
import hexaware.sc.autoinsurance.web.model.BrandDto;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    private BrandMapper brandMapper;
    private BrandRepository brandRepository;
    private JWTUtil jwtUtil;

    @Autowired
    public void setBrandMapper(BrandMapper brandMapper) {
        this.brandMapper = brandMapper;
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
    public Iterable<BrandDto> getAllBrands() {
        return brandMapper.bransToBrandsDtos(brandRepository.findAll());
    }

    @Override
    public BrandDto saveNewBrand(BrandDto brandDto) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1) { // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        }
        Brand brand = brandMapper.brandDtoToBrand(brandDto);
        brand.setCreatedAt(new Date());
        brand.setCreatedBy(tokenData.getUserId());
        return brandMapper.brandToBrandDto(brandRepository.save(brand));
    }

    @Override
    public BrandDto updateBrand(BrandDto brandDto, long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1) { // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        }
        Optional<Brand> brandExist =  brandRepository.findById(id);
        if (!brandExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        Brand brand = brandExist.get();
        brand.setBrandName(brandDto.getBrandName());
        brand.setUpdatedAt(new Date());
        brand.setUpdatedBy(tokenData.getUserId());
        return brandMapper.brandToBrandDto(brandRepository.save(brand));
    }
    
}
