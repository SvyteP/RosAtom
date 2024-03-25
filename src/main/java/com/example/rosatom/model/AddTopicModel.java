package com.example.rosatom.model;

import lombok.Data;

@Data
public class AddTopicModel {
    private String title;
    private String body;
    private Long userId;

    public AddTopicModel(String title, String body, Long userId) {
        this.title = title;
        this.body = body;
        this.userId = userId;
    }
}
