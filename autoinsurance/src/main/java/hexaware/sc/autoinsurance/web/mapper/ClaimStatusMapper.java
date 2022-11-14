package hexaware.sc.autoinsurance.web.mapper;

import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.ClaimStatus;
import hexaware.sc.autoinsurance.web.model.ClaimStatusDto;


@Mapper(
    componentModel = "spring"
)
public interface ClaimStatusMapper {
    
    ClaimStatus claimDtoToClaim(ClaimStatusDto claimStatusDto);

    ClaimStatusDto claimToClaimStatusDto(ClaimStatus claimStatus);

    Iterable<ClaimStatusDto> claimsStatusToClaimsStatusDto(Iterable<ClaimStatus> colors);
}
