package hexaware.sc.autoinsurance.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.services.ClaimSubjectServiceImpl;

@RestController
@RequestMapping("/api/v1/claim_subject")
public class ClaimSubjectController {
    
    private ClaimSubjectServiceImpl claimSubjectService;

    @Autowired
    public void setClaimSubjectService(ClaimSubjectServiceImpl claimSubjectService) {
        this.claimSubjectService = claimSubjectService;
    }
    

    @GetMapping("")
    public ResponseEntity<?> getAll() throws Exception {
        return new ResponseEntity<>( claimSubjectService.getAllSubjects(), HttpStatus.OK);
    }
}
