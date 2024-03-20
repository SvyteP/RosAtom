package com.example.rosatom.model;

import com.example.rosatom.entity.Massage;
import lombok.Data;


@Data
public class MassageModel {
    private Long userId;
    private Long massageId;
    private Long topicId;
    private Massage massage;

    public MassageModel() {
    }

    public MassageModel(Long userId, Long massageId, Long topicId) {
        this.userId = userId;
        this.massageId = massageId;
        this.topicId = topicId;
    }

    public MassageModel(Long userId, Long topicId, Massage massage) {
        this.userId = userId;
        this.topicId = topicId;
        this.massage = massage;
    }

    public MassageModel(Long userId, Long topicId) {
        this.userId = userId;
        this.topicId = topicId;
    }
}
