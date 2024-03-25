package com.example.rosatom.model;

import com.example.rosatom.entity.Topic;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
@Data
public class AllTopicModel {
    private  String title;
    private Long userId;

    public AllTopicModel() {
    }

    public static List<AllTopicModel> convertEntityInModel(List<Topic> list){
        return list.stream()
                .map(topic -> {
                    AllTopicModel allTopicModel = new AllTopicModel();
                    allTopicModel.setTitle(topic.getTitle());
                    allTopicModel.setUserId(topic.getUsers().getId());
                    return allTopicModel;
                })
                .collect(Collectors.toList());
    }
}
