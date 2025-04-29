package com.fitness.activityservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
public class ValidateUserService {

    @Autowired
    private WebClient userServiceWebClient;

    public boolean validateUserByUserId(String userId){

        try{
            return userServiceWebClient
                    .get()
                    .uri("/api/users/{userId}/validate",userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();

        }catch (WebClientResponseException ex){
            if(ex.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("User not found with id "+userId);
            }

        }

        return false;
    }

}
