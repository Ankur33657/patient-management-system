package com.patientmanagementsystem.authservice.services.User;

import com.patientmanagementsystem.authservice.Dto.User.UserResponseDto;
import com.patientmanagementsystem.authservice.Dto.User.userUpdateRequestByAdminDto;
import com.patientmanagementsystem.authservice.Dto.User.userUpdateRequestDto;
import com.patientmanagementsystem.authservice.model.User;
import com.patientmanagementsystem.authservice.repository.UserRepository;
import com.patientmanagementsystem.authservice.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.commonservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


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
       User user=userRepository.findById(userId).orElseThrow(()->new NotFoundException("USER_NOT_FOUND"));
        return UserResponseDto.toUserResponse(user);

    }

    @Override
    public UserResponseDto updateUser(Long userId, userUpdateRequestDto userDto) {
        User user=userRepository.findById(userId).orElseThrow( ()-> new NotFoundException("USER_NOT_FOUND"));
        user.setName(userDto.getName());
        user.setMedia(userDto.getMedia());
        userRepository.save(user);

        return  UserResponseDto.toUserResponse(user);


    }

    @Override
    public UserResponseDto updateUserByAdmin(Long userId, userUpdateRequestByAdminDto userDto) {
        User user=userRepository.findById(userId).orElseThrow();
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setMedia(userDto.getMedia());
        user.setAccountLocked(userDto.isAccountLocked());
        user.setEnabled(userDto.isEnabled());
        user.setRole(userDto.getRole());

        userRepository.save(user);

        return UserResponseDto.toUserResponse(user);
    }



}
