package com.diemdt.identity_service.service;

import com.diemdt.identity_service.dto.request.UserCreationRequest;
import com.diemdt.identity_service.dto.request.UserUpdateRequest;
import com.diemdt.identity_service.dto.response.UserResponse;
import com.diemdt.identity_service.entity.User;
import com.diemdt.identity_service.enums.Role;
import com.diemdt.identity_service.exception.AppException;
import com.diemdt.identity_service.exception.ErrorCode;
import com.diemdt.identity_service.mapper.UserMapper;
import com.diemdt.identity_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final  UserRepository userRepository ;
    private final UserMapper userMapper ;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserCreationRequest request ){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTS_EXCEPTION);
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        List<String> roles = new ArrayList<>();
        roles.add(Role.USER.name());

        user.setRoles(roles);
        User savedUser = userRepository.save(user);

    return userMapper.toDTO(savedUser);
    }

    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    public UserResponse findById(String id) {
       User user =  userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));
       return userMapper.toDTO(user);
    }

    public UserResponse update (String id, UserUpdateRequest request){
        User user =  userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        userMapper.update(user, request);
        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }
}
