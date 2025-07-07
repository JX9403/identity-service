package com.diemdt.identity_service.service;

import com.diemdt.identity_service.dto.request.AuthenticationRequest;
import com.diemdt.identity_service.dto.response.UserResponse;
import com.diemdt.identity_service.entity.User;
import com.diemdt.identity_service.exception.AppException;
import com.diemdt.identity_service.exception.ErrorCode;
import com.diemdt.identity_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    public final UserRepository userRepository ;

    public boolean authenticate (AuthenticationRequest request){
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTS_EXCEPTION));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());
        if(!authenticated){
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }


        return  false;
    }

    private String generateToken (String username){

    }


}
