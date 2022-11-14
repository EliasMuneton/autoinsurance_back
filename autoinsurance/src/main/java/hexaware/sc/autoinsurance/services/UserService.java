package hexaware.sc.autoinsurance.services;


import hexaware.sc.autoinsurance.web.model.UserDto;

public interface UserService {
    
    UserDto getUserById(Long id);

    UserDto saveNewUser(UserDto user) throws Exception;

    UserDto findByEmail(String email) throws Exception;
    
}
