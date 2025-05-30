package com.fitness.aiservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection="recommendation")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    private String Id;
    private String userId;
    private String activityId;
    private String activityType;
    private String recommendation;
    private List<String>improvement;
    private List<String>suggestion;
    private List<String>safety;
    @CreatedDate
    private LocalDateTime createdAt;

}
