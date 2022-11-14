package hexaware.sc.autoinsurance.web.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hexaware.sc.autoinsurance.security.JWTUtil;
import hexaware.sc.autoinsurance.services.UserService;
import hexaware.sc.autoinsurance.web.model.LoginDto;
import hexaware.sc.autoinsurance.web.model.UserDto;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authManager;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody @Validated  LoginDto loginDto) throws Exception {
        UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
            loginDto.getPass());
        authManager.authenticate(authInputToken);
        UserDto userByEmail = userService.findByEmail(loginDto.getEmail());
        String token = jwtUtil.generateToken(userByEmail.getEmail(), userByEmail.getUserId(), userByEmail.getUserRoleId());
        return new ResponseEntity<>(Collections.singletonMap("token", token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerHandler(@RequestBody @Validated  UserDto user) throws Exception {
        UserDto userNew = userService.saveNewUser(user);
        String token = jwtUtil.generateToken(userNew.getEmail(), userNew.getUserId(), userNew.getUserRoleId());
        return new ResponseEntity<>(Collections.singletonMap("token", token), HttpStatus.CREATED);
    }
}
