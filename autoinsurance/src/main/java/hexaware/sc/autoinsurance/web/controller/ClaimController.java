package hexaware.sc.autoinsurance.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.ClaimServiceImpl;
import hexaware.sc.autoinsurance.web.model.ClaimDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/claim")
@SecurityRequirement(name = "token")
public class ClaimController {
    
    private ClaimServiceImpl claimService;

    @Autowired
    public void setClaimService(ClaimServiceImpl claimService) {
        this.claimService = claimService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable long id) throws Exception {
        return new ResponseEntity<>( claimService.getClaimById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody @Validated  ClaimDto claim) throws Exception {        
        return new ResponseEntity<>(claimService.saveNewClaim(claim), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Validated ClaimDto claim) throws Exception {        
        return new ResponseEntity<>( claimService.updateClaim(claim, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) throws Exception {
        return new ResponseEntity<>( claimService.deleteClaim(id), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> seacrh(@RequestBody  ClaimDto claim) throws Exception {        
        return new ResponseEntity<>( claimService.searchClaims(claim), HttpStatus.OK);
    }
}
