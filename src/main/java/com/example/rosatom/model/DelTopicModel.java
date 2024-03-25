package com.example.rosatom.model;

import lombok.Data;

@Data
public class DelTopicModel {
    private Long userId;
    private Long topicId;
    public DelTopicModel(Long userId, Long topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }

}
