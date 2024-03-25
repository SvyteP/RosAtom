package com.example.rosatom.model;

import lombok.Data;

@Data
public class EditTopicModel {
    private Long topicId;
    private  Long userId;
    private String title;
}
