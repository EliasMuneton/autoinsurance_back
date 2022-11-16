package hexaware.sc.autoinsurance.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hexaware.sc.autoinsurance.domain.ClaimSubject;
import hexaware.sc.autoinsurance.domain.Token;
import hexaware.sc.autoinsurance.repositories.ClaimSubjectRepository;
import hexaware.sc.autoinsurance.security.JWTUtil;
import hexaware.sc.autoinsurance.web.mapper.ClaimSubjectMapper;
import hexaware.sc.autoinsurance.web.model.ClaimSubjectDto;

@Service
public class ClaimSubjectServiceImpl implements ClaimSubjectService {

    private ClaimSubjectMapper claimSubjectMapper;
    private ClaimSubjectRepository claimSubjectRepository;
    private JWTUtil jwtUtil;

    @Autowired
    public void setClaimSubjectMapper(ClaimSubjectMapper claimSubjectMapper) {
        this.claimSubjectMapper = claimSubjectMapper;
    }

    @Autowired
    public void setClaimSubjectRepository(ClaimSubjectRepository claimSubjectRepository) {
        this.claimSubjectRepository = claimSubjectRepository;
    }

    @Autowired
    public void setTokenUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Iterable<ClaimSubjectDto> getAllSubjects() {
        return claimSubjectMapper.claimSubjectsToClaimSubjectsDtos(claimSubjectRepository.findAll());
    }

    @Override
    public ClaimSubjectDto saveNewClaimSubject(ClaimSubjectDto claimSubjectDto) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1) // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        ClaimSubject claimSubject = claimSubjectMapper.claimSubjectDtoToClaimSubject(claimSubjectDto);
        claimSubject.setCreatedAt(new Date());
        claimSubject.setCreatedBy(tokenData.getUserId());
        return claimSubjectMapper.claimSubjectToClasimSubjectDto(claimSubjectRepository.save(claimSubject));
    }

    @Override
    public ClaimSubjectDto updateClaimSubject(ClaimSubjectDto claimSubjectDto, long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        if (tokenData.getUserRolId() != 1)  // Valid just for ADMIN
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You do not have grant access");
        Optional<ClaimSubject> claimSubjectExist = claimSubjectRepository.findById(id);
        if (!claimSubjectExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        ClaimSubject claimSubject = claimSubjectExist.get();
        claimSubject.setDescription(claimSubjectDto.getDescription());
        claimSubject.setUpdatedAt(new Date());
        claimSubject.setUpdatedBy(tokenData.getUserId());
        return claimSubjectMapper.claimSubjectToClasimSubjectDto(claimSubjectRepository.save(claimSubject));
    }

}
