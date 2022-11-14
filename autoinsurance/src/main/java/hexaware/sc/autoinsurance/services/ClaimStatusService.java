package hexaware.sc.autoinsurance.services;

import hexaware.sc.autoinsurance.web.model.ClaimStatusDto;

public interface ClaimStatusService {

    Iterable<ClaimStatusDto> getAllClaimStatus();
}
