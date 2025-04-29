package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.exception.ExistingUserException;
import com.fitness.userservice.exception.ResourceNotFoundException;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public UserResponse register(@Valid RegisterRequest request) {

        if(userRepo.existsByEmail(request.getEmail())){
            throw  new ExistingUserException("user already exists");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());

        User saveUser=userRepo.save(user);

        return getUserResponse(saveUser);
    }

    public UserResponse getUserProfile(String userId) {

        User user=userRepo.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User Id","userId",userId));
        return getUserResponse(user);
    }

    private UserResponse getUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreated(user.getCreated());
        userResponse.setUpdated(user.getUpdated());
        return userResponse;
    }

    public Boolean existByUserId(String userId) {
        return userRepo.existsById(userId);
    }
}
