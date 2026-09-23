package com.patientmanagementsystem.authservice.services.User;

import com.patientmanagementsystem.authservice.Dto.User.UserResponseDto;
import com.patientmanagementsystem.authservice.Dto.types.TokenResponse;
import com.patientmanagementsystem.authservice.model.User;
import com.patientmanagementsystem.authservice.repository.UserRepository;
import com.patientmanagementsystem.authservice.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class userServiceImpl implements UserService {


    private  final UserRepository userRepository;
   private  final JwtUtils jwtUtils;
    public userServiceImpl(UserRepository userRepository, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }


    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for(User user:users){
            userResponseDtos.add(UserResponseDto.toUserResponse(user));
        }
        return userResponseDtos;
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
       User user=userRepository.findById(userId).orElseThrow();
        return UserResponseDto.toUserResponse(user);

    }

    @Override
    public UserResponseDto updateUser(Long userId, UserResponseDto userDto) {
        User user=userRepository.findById(userId).orElseThrow();
        user.setName(userDto.getName());
        user.setEnabled(userDto.isEnabled());
        user.setMedia(userDto.getMedia());
        user.setAccountLocked(userDto.isAccountLocked());
        userRepository.save(user);

        return  UserResponseDto.toUserResponse(user);


    }



}
