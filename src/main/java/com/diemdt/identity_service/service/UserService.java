package com.diemdt.identity_service.service;

import com.diemdt.identity_service.dto.request.UserCreationRequest;
import com.diemdt.identity_service.dto.request.UserUpdateRequest;
import com.diemdt.identity_service.dto.response.RoleResponse;
import com.diemdt.identity_service.dto.response.UserResponse;
import com.diemdt.identity_service.entity.User;
import com.diemdt.identity_service.enums.Role;
import com.diemdt.identity_service.exception.AppException;
import com.diemdt.identity_service.exception.ErrorCode;
import com.diemdt.identity_service.mapper.UserMapper;
import com.diemdt.identity_service.repository.RoleRepository;
import com.diemdt.identity_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final  UserRepository userRepository ;
    private final UserMapper userMapper ;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository ;

    public UserResponse create(UserCreationRequest request ){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTS_EXCEPTION);
        }

        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());

        user.setRoles(new HashSet<>(roles));
        User savedUser = userRepository.save(user);

    return userMapper.toDTO(savedUser);
    }

    public Page<UserResponse> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::toDTO);
    }

    public UserResponse getMyInfo(){
        SecurityContext context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTS_EXCEPTION));

        return userMapper.toDTO(user);
    }

    public UserResponse findById(String id) {
       User user =  userRepository.findById(id).orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTS_EXCEPTION));
       return userMapper.toDTO(user);
    }

    public UserResponse update (String id, UserUpdateRequest request){
        log.info(request.toString());
        User user =  userRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTS_EXCEPTION));
    System.out.println("_____________"+request.getRoles());
        userMapper.update(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());

        user.setRoles(new HashSet<>(roles));

        User updatedUser = userRepository.save(user);
        return userMapper.toDTO(updatedUser);
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }
}
