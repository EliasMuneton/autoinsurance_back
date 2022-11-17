package hexaware.sc.autoinsurance.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hexaware.sc.autoinsurance.repositories.ClaimStatusRepository;
import hexaware.sc.autoinsurance.web.mapper.ClaimStatusMapper;
import hexaware.sc.autoinsurance.web.model.ClaimStatusDto;

@Service
@Transactional
public class ClaimStatusServiceImpl implements ClaimStatusService {

    private ClaimStatusMapper claimStatusMapper;
    private ClaimStatusRepository claimStatusRepository;

    @Autowired
    public void setClaimStatusMapper(ClaimStatusMapper claimStatusMapper) {
        this.claimStatusMapper = claimStatusMapper;
    }

    @Autowired
    public void setClaimStatusRepository(ClaimStatusRepository claimStatusRepository) {
        this.claimStatusRepository = claimStatusRepository;
    }
    
    @Override
    public Iterable<ClaimStatusDto> getAllClaimStatus() {
        return claimStatusMapper.claimsStatusToClaimsStatusDto(claimStatusRepository.findAll());
    }
    
}
