package hexaware.sc.autoinsurance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.BrandServiceImpl;
import hexaware.sc.autoinsurance.web.model.BrandDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/brand")
@SecurityRequirement(name = "token")
public class BrandController {
    
    private BrandServiceImpl brandService;

    @Autowired
    public void setClaimService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>( brandService.getAllBrands(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody @Validated  BrandDto brand) throws Exception {        
        return new ResponseEntity<>( brandService.saveNewBrand(brand), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Validated BrandDto brand) throws Exception {        
        return new ResponseEntity<>( brandService.updateBrand(brand, id), HttpStatus.OK);
    }
}
