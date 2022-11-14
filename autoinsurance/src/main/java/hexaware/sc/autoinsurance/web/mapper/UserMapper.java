package hexaware.sc.autoinsurance.web.mapper;

import org.mapstruct.Mapper;

import hexaware.sc.autoinsurance.domain.User;
import hexaware.sc.autoinsurance.web.model.UserDto;

@Mapper(
    componentModel = "spring"
)
public interface UserMapper {
    
    UserDto userToUserDto(User user);

    User userDtoToUser(UserDto userDto);
}
