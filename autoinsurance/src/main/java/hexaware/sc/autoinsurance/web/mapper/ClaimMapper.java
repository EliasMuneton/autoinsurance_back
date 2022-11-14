package hexaware.sc.autoinsurance.web.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.Claim;
import hexaware.sc.autoinsurance.web.model.ClaimDto;

@Mapper(
    componentModel = "spring"
)
public interface ClaimMapper {
    
    ClaimDto claimToClaimDto(Claim claim);

    Claim claimDtoToClaim(ClaimDto claimDto);

    Iterable<ClaimDto> claimsToClaimsDto(List<Claim> vehicles);
}