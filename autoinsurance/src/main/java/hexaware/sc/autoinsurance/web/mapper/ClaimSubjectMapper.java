package hexaware.sc.autoinsurance.web.mapper;

import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.ClaimSubject;
import hexaware.sc.autoinsurance.web.model.ClaimSubjectDto;

@Mapper(
    componentModel = "spring"
)
public interface ClaimSubjectMapper {
    
    ClaimSubject claimSubjectDtoToClaimSubject(ClaimSubjectDto claimSubjectDto);

    ClaimSubjectDto claimSubjectToClasimSubjectDto(ClaimSubject claimSubject);

    Iterable<ClaimSubjectDto> claimSubjectsToClaimSubjectsDtos(Iterable<ClaimSubject> claimSubject);
}
