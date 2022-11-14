package hexaware.sc.autoinsurance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.BrandServiceImpl;

@RestController
@RequestMapping("/api/v1/brand")
public class BrandController {
    
    private BrandServiceImpl brandService;

    @Autowired
    public void setClaimService(BrandServiceImpl brandService) {
        this.brandService = brandService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>( brandService.getAllBrands(), HttpStatus.OK);
    }
}
