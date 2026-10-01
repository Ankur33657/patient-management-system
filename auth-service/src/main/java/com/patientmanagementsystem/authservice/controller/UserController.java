package com.patientmanagementsystem.authservice.controller;

import com.patientmanagementsystem.authservice.Dto.User.*;
import com.patientmanagementsystem.authservice.Dto.types.Role;
import com.patientmanagementsystem.authservice.services.User.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> GetCurrentUser(@RequestHeader("X-User-ID") String currentUserId) {
        Long id=Long.valueOf(currentUserId);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(id));
    }



    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateCurrentUser(@RequestHeader("X-User-ID") String currentUserId,@RequestBody  userUpdateRequestDto userDto){
        Long id=Long.valueOf(currentUserId);
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(id,userDto));
    }

    @PutMapping("/me/password")
    public ResponseEntity<String> updatedPassword(@RequestHeader("X-User-Email") String currentUserEmail,@RequestBody passwordUpdateRequestDto password){
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.updatePassword(currentUserEmail,password));
    }



    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{userId}")
    public  ResponseEntity<String> deleteUserById(@RequestHeader("X-User-ID") String currentUserId, @PathVariable Long userId){
        if(currentUserId.equals(userId.toString())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("USER_PERMISSION_DENIED_TO_DELETE_OWN_ACCOUNT");
        }
        return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.deleteUserById(userId));
    }



    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping
    public ResponseEntity<Page<UserResponseDto>>GetAllUsers(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size,@RequestParam(defaultValue = "id") String sortBy){
        Sort sort= Sort.by(Sort.Direction.fromString("asc"),sortBy);
        Pageable pageable=PageRequest.of(page,size,sort);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers(pageable));
    }


    @PreAuthorize("hasAnyRole('DOCTOR','ADMIN','SUPERVISOR')")
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> GetUserById(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserById(userId));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Long userId,@RequestBody userUpdateRequestByAdminDto userDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserByAdmin(userId,userDto));
    }




    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping("/role")
    public ResponseEntity<String> getUserRole(
            @RequestHeader("X-User-Role") String role) {
        return ResponseEntity.status(HttpStatus.OK).body(role);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{userId}/role")
    public ResponseEntity<UserResponseDto> updateRole(@PathVariable Long userId, @RequestBody Role role){
        return  ResponseEntity.status(HttpStatus.OK).body(userService.updateUserRole(userId,role));
    }

    @PreAuthorize("hasAnyRole('DOCTOR','ADMIN','SUPERVISOR')")
    @GetMapping("/search")
    public ResponseEntity<Page<UserResponseDto>> SearchUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size,
                                                           @RequestParam(defaultValue = "name") String sortBy, @RequestParam(defaultValue = "asc") String sortDirection){
        Sort sort=Sort.by(Sort.Direction.fromString(sortDirection),sortBy);
        Pageable pageable= PageRequest.of(page,size,sort);
        return ResponseEntity.status(HttpStatus.OK).body(userService.SearchUser(pageable));
    }

    @PreAuthorize("hasAnyRole('ADMIN','SUPERVISOR')")
    @GetMapping("/account-status")
    public  ResponseEntity<Page<UserResponseDto>> getAllInactiveOrLockedUser(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "20") int size){
        Sort sort=Sort.by(Sort.Direction.fromString("asc"),"id");
        Pageable pageable=PageRequest.of(page,size,sort);
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllInactiveOrLockedUser(pageable));

    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/{userId}/change-status")
    public ResponseEntity<UserResponseDto> inactiveUser(@PathVariable Long userId){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.changeInactiveStatus(userId));
    }





}
