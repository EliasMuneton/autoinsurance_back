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

import hexaware.sc.autoinsurance.services.ClaimSubjectServiceImpl;
import hexaware.sc.autoinsurance.web.model.ClaimSubjectDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/v1/claim_subject")
@SecurityRequirement(name = "token")
public class ClaimSubjectController {
    
    private ClaimSubjectServiceImpl claimSubjectService;

    @Autowired
    public void setClaimSubjectService(ClaimSubjectServiceImpl claimSubjectService) {
        this.claimSubjectService = claimSubjectService;
    }
    

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>( claimSubjectService.getAllSubjects(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> register(@RequestBody @Validated  ClaimSubjectDto claimSubject) throws Exception {        
        return new ResponseEntity<>( claimSubjectService.saveNewClaimSubject(claimSubject), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody @Validated ClaimSubjectDto claimSubject) throws Exception {        
        return new ResponseEntity<>( claimSubjectService.updateClaimSubject(claimSubject, id), HttpStatus.OK);
    }
}
