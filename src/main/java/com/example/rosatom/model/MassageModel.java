package com.example.rosatom.model;

public class MassageModel {
    private Long userId;
    private Long massageId;
    private Long topicId;

    public MassageModel(Long userId, Long massageId, Long topicId) {
        this.userId = userId;
        this.massageId = massageId;
        this.topicId = topicId;
    }

    public MassageModel(Long userId, Long topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }
}
