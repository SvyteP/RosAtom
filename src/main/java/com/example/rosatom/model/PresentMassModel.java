package com.example.rosatom.model;

import com.example.rosatom.entity.Massage;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PresentMassModel {
    private String data;
    private String body;
    private Long userId;
    private Long topicId;

    public PresentMassModel() {
    }
    public PresentMassModel(Massage massage) {
        this.data = massage.getDate();
        this.body = massage.getBody();
        this.userId = massage.getUsers().getId();
        this.topicId = massage.getTopic().getId();
    }

    public static List<PresentMassModel> convertEntityInModel(List<Massage> list){
        return list.stream()
                .map(massage -> {
                    PresentMassModel presentMassModel = new PresentMassModel();
                    presentMassModel.setBody(massage.getBody());
                    presentMassModel.setUserId(massage.getUsers().getId());
                    presentMassModel.setData(massage.getDate());
                    presentMassModel.setTopicId(massage.getTopic().getId());
                    return presentMassModel;
                })
                .collect(Collectors.toList());
    }
}
