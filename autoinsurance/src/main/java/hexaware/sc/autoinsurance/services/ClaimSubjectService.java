package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.ClaimSubjectDto;

public interface ClaimSubjectService {
    
    Iterable<ClaimSubjectDto> getAllSubjects();
}
