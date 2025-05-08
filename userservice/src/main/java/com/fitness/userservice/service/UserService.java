package com.fitness.userservice.service;

import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.exception.ResourceNotFoundException;
import com.fitness.userservice.model.User;
import com.fitness.userservice.repository.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    public UserResponse register(@Valid RegisterRequest request) {

        if(userRepo.existsByEmail(request.getEmail())){
            return getUserResponse(userRepo.getUserByEmail(request.getEmail()));
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

    public Boolean existByUserId(String userId) {
        return userRepo.existsByKeyCloakId(userId);
    }



    //setting userResponse helper
    private UserResponse getUserResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setKeyCloakId(user.getKeyCloakId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreated(user.getCreated());
        userResponse.setUpdated(user.getUpdated());
        return userResponse;
    }


}
