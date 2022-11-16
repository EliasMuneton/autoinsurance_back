package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.ClaimSubjectDto;

public interface ClaimSubjectService {
    
    Iterable<ClaimSubjectDto> getAllSubjects();

    ClaimSubjectDto saveNewClaimSubject(ClaimSubjectDto claimSubjectDto) throws Exception;

    ClaimSubjectDto updateClaimSubject(ClaimSubjectDto claimSubjectDto, long id) throws Exception;
}
