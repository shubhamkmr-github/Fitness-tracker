package com.fitness.activityservice.service;


import com.fitness.activityservice.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.exception.NotValidUserException;
import com.fitness.activityservice.exception.ResourceNotFoundException;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ActivityService {
    final ActivityRepository activityRepository;
    final ValidateUserService validateUserService;
    final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.key}")
    private String routing;

    public ActivityResponse trackActivity(ActivityRequest request) {
        Boolean isValid=validateUserService.validateUserByUserId(request.getUserId());
        if(!isValid){
            throw new NotValidUserException("No user found with id:"+request.getUserId());
        }
        Activity activity=Activity .builder()
                .userId(request.getUserId())
                .type(request.getActivityType())
                .duration(request.getDuration())
                .startTime(request.getStartTime())
                .caloriesBurned(request.getCaloriesBurned())
                .additionalMetrics(request.getAdditionalMatrics())
                .build();
        Activity saveActivity=activityRepository.save(activity);
        try{
            rabbitTemplate.convertAndSend(exchange, routing, saveActivity);
        }catch(Exception e){
            log.error("failed to upload to RabbitMQ");
        }
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
