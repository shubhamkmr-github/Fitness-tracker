package com.fitness.aiservice.controller;


import com.fitness.aiservice.model.Recommendation;
import com.fitness.aiservice.service.RecommendationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/recommendation")
public class RecommendationController {
    private RecommendationService recommendationService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendation>> getUserRecommendation(String userId){
        return ResponseEntity.ok(recommendationService.getUserRecommendation(userId));
    }

    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendation> getActivityRecommendation(String activityId){
        return ResponseEntity.ok(recommendationService.getActivityRecommendation(activityId));
    }
}
