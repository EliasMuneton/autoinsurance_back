package hexaware.sc.autoinsurance.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hexaware.sc.autoinsurance.domain.Claim;
import hexaware.sc.autoinsurance.domain.ClaimStatus;
import hexaware.sc.autoinsurance.domain.ClaimSubject;
import hexaware.sc.autoinsurance.domain.EmailSender;
import hexaware.sc.autoinsurance.domain.Token;
import hexaware.sc.autoinsurance.domain.Vehicle;
import hexaware.sc.autoinsurance.repositories.ClaimRepository;
import hexaware.sc.autoinsurance.repositories.ClaimStatusRepository;
import hexaware.sc.autoinsurance.repositories.ClaimSubjectRepository;
import hexaware.sc.autoinsurance.repositories.VehicleRepository;
import hexaware.sc.autoinsurance.security.JWTUtil;
import hexaware.sc.autoinsurance.web.mapper.ClaimMapper;
import hexaware.sc.autoinsurance.web.model.ClaimDto;

@Service
@Transactional
public class ClaimServiceImpl implements ClaimService {

    private ClaimRepository claimRepository;
    private ClaimMapper claimMapper;
    private ClaimSubjectRepository claimSubjectRepository;
    private ClaimStatusRepository claimStatusRepository;
    private VehicleRepository vehicleRepository;
    private MailerService mailerService;
    private JWTUtil jwtUtil;

    @Autowired
    public void setClaimRepository(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Autowired
    public void setClaimMapper(ClaimMapper claimMapper) {
        this.claimMapper = claimMapper;
    }

    @Autowired
    public void setClaimSubjectrepository(ClaimSubjectRepository claimSubjectRepository) {
        this.claimSubjectRepository = claimSubjectRepository;
    }

    @Autowired
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Autowired
    public void setClaimStatusRepositpry(ClaimStatusRepository claimStatusRepository) {
        this.claimStatusRepository = claimStatusRepository;
    }

    @Autowired
    public void setMailerService(MailerService mailerService) {
        this.mailerService = mailerService;
    }
    

    @Autowired
    public void setTokenUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PersistenceContext
    private EntityManager em;

    
    @Override
    public ClaimDto getClaimById(long id) throws Exception {
        Optional<Claim> claimById = claimRepository.findById(id);
        if (claimById.isPresent())
            return claimMapper.claimToClaimDto(claimById.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID " + id + " not found");
    }

    @Override
    public ClaimDto saveNewClaim(ClaimDto claimDto) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        Claim claim = claimMapper.claimDtoToClaim(claimDto);
        claim = validStatusAndSubject(claim);
        claim.setCreatedAt(new Date());
        claim.setCreatedBy(tokenData.getUserId());
        EmailSender emailSender = new EmailSender(tokenData.getEmail(), "" , "Claim Register");
        mailerService.sendEmailClaim(emailSender, claim, "Registered");
        return claimMapper.claimToClaimDto(claimRepository.save(claim));
    }

    @Override
    public ClaimDto updateClaim(ClaimDto claimDto, long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        Optional<Claim> claimExist =  claimRepository.findById(id);
        if (!claimExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        Claim claim = claimExist.get();
        boolean isAttended = false;
        if (claim.getClaimStatusId() == 1 && claimDto.getClaimStatusId() == 2) //Valid if claim status is attended
            isAttended = true;
        claim.setClaimStatusId(claimDto.getClaimStatusId());
        claim.setClaimSubjectId(claimDto.getClaimSubjectId());
        claim.setDescription(claimDto.getDescription());
        claim = validStatusAndSubject(claim);
        claim.setUpdatedAt(new Date());
        claim.setUpdatedBy(tokenData.getUserId());
        if (isAttended) {
            EmailSender emailSender = new EmailSender(claim.getVehicle().getUser().getEmail(), "" , "Claim Attended");
            mailerService.sendEmailClaim(emailSender, claim, "Attended");
        }
        return claimMapper.claimToClaimDto(claimRepository.save(claim));
    }

    @Override
    public boolean deleteClaim(long id) throws Exception {
        Token tokenData = jwtUtil.geTokenData();
        Optional<Claim> claimByIdExist = claimRepository.findById(id);
        if (!claimByIdExist.isPresent()) 
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "ID " + id + " not found");
        Claim claimById = claimByIdExist.get();
        claimById.setDeletedAt(new Date());
        claimById.setDeletedBy(tokenData.getUserId());
        claimRepository.save(claimById);
        return true;
    }

    public Claim validStatusAndSubject(Claim claim) {
        Optional<ClaimSubject> existClaimSubject = claimSubjectRepository.findById(claim.getClaimSubjectId());
        if (!existClaimSubject.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID Subject " + claim.getClaimSubjectId() + " not found");
        claim.setClaimSubject(existClaimSubject.get());

        Optional<ClaimStatus> existsClaimStatus = claimStatusRepository.findById(claim.getClaimStatusId());
        if (!existsClaimStatus.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID Status " + claim.getClaimStatusId() + " not found");
        claim.setClaimStatus(existsClaimStatus.get());

        Optional<Vehicle> existVehicle = vehicleRepository.findById(claim.getVehicleId());
        if (!existVehicle.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ID Vehicle " + claim.getVehicleId() + " not found");
        claim.setVehicle(existVehicle.get());
        return claim;
    }

    @Override
    public Iterable<ClaimDto> searchClaims(ClaimDto claimDto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Claim> cq = cb.createQuery(Claim.class);

        Root<Claim> claim = cq.from(Claim.class);
        List<Predicate> predicates = new ArrayList<>();

        if (claimDto.getClaimStatusId() > 0) {
            predicates.add(cb.equal(claim.get("claimStatusId"), claimDto.getClaimStatusId()));
        }

        if (claimDto.getClaimSubjectId() > 0) {
            predicates.add(cb.equal(claim.get("claimSubjectId"), claimDto.getClaimSubjectId()));
        }

        if (claimDto.getVehicleId() > 0) {
            predicates.add(cb.equal(claim.get("vehicleId"), claimDto.getVehicleId()));
        }

        Token tokenData = jwtUtil.geTokenData();
        if ( tokenData.getUserRolId() != 1) {
            predicates.add(cb.equal(claim.get("createdBy"), tokenData.getUserId()));
        }
        
       
        cq.where(predicates.toArray(new Predicate[0]));

        return claimMapper.claimsToClaimsDto(em.createQuery(cq).getResultList());
    }
}