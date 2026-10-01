package com.patientmanagementsystem.authservice.services.User;

import com.patientmanagementsystem.authservice.Dto.User.UserResponseDto;
import com.patientmanagementsystem.authservice.Dto.User.passwordUpdateRequestDto;
import com.patientmanagementsystem.authservice.Dto.User.userUpdateRequestByAdminDto;
import com.patientmanagementsystem.authservice.Dto.User.userUpdateRequestDto;
import com.patientmanagementsystem.authservice.Dto.types.Role;
import com.patientmanagementsystem.authservice.model.User;
import com.patientmanagementsystem.authservice.repository.UserRepository;
import com.patientmanagementsystem.authservice.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.example.commonservice.exception.NotFoundException;
import org.example.commonservice.exception.UnAuthorizedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class userServiceImpl implements UserService {


    private  final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
   private  final JwtUtils jwtUtils;
    public userServiceImpl(UserRepository userRepository, JwtUtils jwtUtils,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public Page<UserResponseDto> getAllUsers(Pageable pageable) {
        Page<User> users=userRepository.findAllUser(pageable);
        return  users.map(user->UserResponseDto.toUserResponse(user));
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

    @Override
    public String deleteUserById(Long userId) {
        User user=userRepository.findById(userId).orElseThrow(()->new NotFoundException("USER_NOT_FOUND"));
        userRepository.delete(user);
        return "USER_DELETED_SUCCESSFULLY";
    }

    @Override
    public UserResponseDto updateUserRole(Long userId, Role role) {
        User user= userRepository.findById(userId).orElseThrow(()->new NotFoundException("USER_NOT_FOUND"));
        user.setRole(role);
        userRepository.save(user);
        return UserResponseDto.toUserResponse(user);
    }

    @Override
    public  Page<UserResponseDto> SearchUser(Pageable pageable){
        Page<User> users=userRepository.searchByKeyword(pageable);
        return  users.map(user->UserResponseDto.toUserResponse(user));
    }

    @Override
    public  String updatePassword(String email,passwordUpdateRequestDto password){
     User user=userRepository.findByEmail(email).orElseThrow(()->new UnAuthorizedException("UNAUTHORISED_ACCESS"));
     boolean isPasswordCorrect= passwordEncoder.matches(password.getOld_password(),user.getPassword());
     if(!isPasswordCorrect)return "INCORECT_OLD_PASSWORD";
     user.setPassword(passwordEncoder.encode(password.getNew_password()));
     return "PASSWORD_UPDATED_SUCCESSFULLY";
    }

    @Override
    public Page<UserResponseDto> getAllInactiveOrLockedUser(Pageable pageable){
        return  null;
    }

    @Override
    public  UserResponseDto changeInactiveStatus(Long userId){
        return  null;
    }





}
