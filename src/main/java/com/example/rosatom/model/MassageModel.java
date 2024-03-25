package com.example.rosatom.model;

import lombok.Data;


@Data
public class MassageModel {
    private String body;
    private Long userId;
    private Long massageId;
    private Long topicId;


    public MassageModel() {
    }

    public MassageModel(String body, Long userId, Long massageId, Long topicId) {
        this.body = body;
        this.userId = userId;
        this.massageId = massageId;
        this.topicId = topicId;
    }
}
