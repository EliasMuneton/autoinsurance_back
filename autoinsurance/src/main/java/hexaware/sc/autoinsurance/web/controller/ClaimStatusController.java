package hexaware.sc.autoinsurance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.ClaimStatusServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/claim_status")
@SecurityRequirement(name = "token")
public class ClaimStatusController {
    
   private ClaimStatusServiceImpl claimStatusService;

    @Autowired
    public void setClaimStatusService(ClaimStatusServiceImpl claimStatusService) {
        this.claimStatusService = claimStatusService;
    }
    

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>( claimStatusService.getAllClaimStatus(), HttpStatus.OK);
    }

}
