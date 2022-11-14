package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.ClaimDto;

public interface ClaimService {
    

    ClaimDto getClaimById(long id) throws Exception;

    ClaimDto saveNewClaim(ClaimDto claim) throws Exception;

    ClaimDto updateClaim(ClaimDto claim, long id) throws Exception;

    boolean deleteClaim(long id) throws Exception;

    Iterable<ClaimDto> searchClaims(ClaimDto claimDto);
}