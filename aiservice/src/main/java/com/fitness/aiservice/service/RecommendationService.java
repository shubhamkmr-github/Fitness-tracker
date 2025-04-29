package com.fitness.aiservice.service;


import com.fitness.aiservice.Repo.RecommendationRepo;
import com.fitness.aiservice.model.Recommendation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final RecommendationRepo recommendationRepo;

    public List<Recommendation> getUserRecommendation(String userId) {
        return recommendationRepo.findByuserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return recommendationRepo.findByactivityId(activityId)
                .orElseThrow(()->new RuntimeException("no activity found"));
    }
}
