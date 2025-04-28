package com.fitness.activityservice.service;


import com.fitness.activityservice.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.exception.ResourceNotFoundException;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {
    final ActivityRepository activityRepository;

    public ActivityResponse trackActivity(ActivityRequest request) {

        Activity activity=Activity .builder()
                .userId(request.getUserId())
                .type(request.getActivityType())
                .duration(request.getDuration())
                .startTime(request.getStartTime())
                .caloriesBurned(request.getCaloriesBurned())
                .additionalMetrics(request.getAdditionalMatrics())
                .build();
        Activity saveActivity=activityRepository.save(activity);
        return toResponseActivity(saveActivity);
    }

    public ActivityResponse toResponseActivity(Activity activity) {
        ActivityResponse activityResponse=new ActivityResponse();
        activityResponse.setId(activity.getId());
        activityResponse.setUserId(activity.getUserId());
        activityResponse.setType(activity.getType());
        activityResponse.setDuration(activity.getDuration());
        activityResponse.setStartTime(activity.getStartTime());
        activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
        activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
        activityResponse.setCreatedAt(activity.getCreatedAt());
        activityResponse.setUpdatedAt(activity.getUpdatedAt());
        return activityResponse;
    }

    public List<ActivityResponse> getUserActivities(String userId) {

        List<Activity> activities=activityRepository.findByUserId(userId);

        return activities.stream()
                .map(this::toResponseActivity)
                .collect(Collectors.toList());
    }

    public ActivityResponse getActivity(String activityId) {

        Activity activity=activityRepository.findById(activityId).orElseThrow(() -> new ResourceNotFoundException("activity","activityId",activityId));
        return toResponseActivity(activity);
    }
}
