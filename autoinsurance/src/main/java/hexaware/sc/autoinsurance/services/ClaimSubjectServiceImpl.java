package hexaware.sc.autoinsurance.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hexaware.sc.autoinsurance.repositories.ClaimSubjectRepository;
import hexaware.sc.autoinsurance.web.mapper.ClaimSubjectMapper;
import hexaware.sc.autoinsurance.web.model.ClaimSubjectDto;

@Service
public class ClaimSubjectServiceImpl implements ClaimSubjectService{

    private ClaimSubjectMapper claimSubjectMapper;
    private ClaimSubjectRepository claimSubjectRepository;

    @Autowired
    public void setClaimSubjectMapper(ClaimSubjectMapper claimSubjectMapper) {
        this.claimSubjectMapper = claimSubjectMapper;
    }

    @Autowired
    public void setClaimSubjectRepository(ClaimSubjectRepository claimSubjectRepository) {
        this.claimSubjectRepository = claimSubjectRepository;
    }

    @Override
    public Iterable<ClaimSubjectDto> getAllSubjects() {
        return claimSubjectMapper.claimSubjectsToClaimSubjectsDtos(claimSubjectRepository.findAll());
    }
    
}
