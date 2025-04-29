package com.fitness.activityservice.controller;


import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import com.fitness.activityservice.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/activity")
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest request) {
        return new ResponseEntity<>(activityService.trackActivity(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ActivityResponse>> getUserActivities(@RequestHeader ("X-User-Id") String userId) {
        return new ResponseEntity<>(activityService.getUserActivities(userId),HttpStatus.OK);
    }

    @GetMapping("{activityId}")
    public ResponseEntity<ActivityResponse> getActivity(@PathVariable String activityId) {
        return new ResponseEntity<>(activityService.getActivity(activityId), HttpStatus.OK);
    }

}
