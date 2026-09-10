package com.patientmanagementsystem.authservice.services.User;

import com.patientmanagementsystem.authservice.Dto.auth.UserCreateRequestDto;
import com.patientmanagementsystem.authservice.Dto.User.UserResponseDto;
import com.patientmanagementsystem.authservice.model.User;
import com.patientmanagementsystem.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class userServiceImpl implements UserService {


    private  final UserRepository userRepository;

    public userServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
}
