package com.example.rosatom.model;

import com.example.rosatom.entity.Massage;
import com.example.rosatom.entity.Topic;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PresentTopicModel {
    private  String title;
    private Long userId;
    private  List<PresentMassModel> topic_mass;

    public PresentTopicModel() {
    }


    public PresentTopicModel(String title, List<PresentMassModel> topic_mass) {
        this.title = title;
        this.topic_mass = topic_mass;
    }

    public static List<PresentTopicModel> convertEntityInModel(List<Topic> list){
        return list.stream()
                .map(topic -> {
                    PresentTopicModel presentTopicModel = new PresentTopicModel();
                    presentTopicModel.setTitle(topic.getTitle());
                    presentTopicModel.setUserId(topic.getUsers().getId());
                    return presentTopicModel;
                })
                .collect(Collectors.toList());
    }
}
