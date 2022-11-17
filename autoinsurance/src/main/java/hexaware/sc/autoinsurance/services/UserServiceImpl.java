package hexaware.sc.autoinsurance.services;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import hexaware.sc.autoinsurance.domain.User;
import hexaware.sc.autoinsurance.repositories.UserRepository;
import hexaware.sc.autoinsurance.web.mapper.UserMapper;
import hexaware.sc.autoinsurance.web.model.UserDto;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> userById = userRepository.findById(id);
        if (userById.isPresent())
            return userMapper.userToUserDto(userById.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID  " + id + " not found");
    }

    @Override
    public UserDto saveNewUser(UserDto userDto)  throws Exception {
        User user = userMapper.userDtoToUser(userDto);
        user.setCreatedAt(new Date());
        Optional<User> userRes = userRepository.findByEmail(user.getEmail());
        if (userRes.isEmpty()) {
            String encodedPass = passwordEncoder.encode(user.getPass());
            user.setPass(encodedPass);
            return userMapper.userToUserDto(userRepository.save(user));
        }
        throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Email " + user.getEmail() +  " already Exists");
    }

    @Override
    public UserDto findByEmail(String email) throws Exception{
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent())
            return userMapper.userToUserDto(userByEmail.get());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email " + email + " not found");
    }

}