package com.fitness.aiservice.service;


import com.fitness.aiservice.Repo.RecommendationRepo;
import com.fitness.aiservice.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import com.fitness.aiservice.model.Recommendation;
@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityMessageListener {
    private final RecommendationRepo recommendationRepo;
    private final ActivityAiService activityAiService;
    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {

        log.info("listening for activity: {}", activity.getId());
        Recommendation recommendation=activityAiService.generateRecommendation(activity);
        recommendationRepo.save(recommendation);


    }
}
