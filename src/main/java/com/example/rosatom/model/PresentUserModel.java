package com.example.rosatom.model;

import com.example.rosatom.entity.Topic;
import com.example.rosatom.entity.User;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class PresentUserModel {
    private String name;
    private List<AllTopicModel> topics;
    private  List<PresentMassModel> massages;


    public PresentUserModel() {
    }

    public PresentUserModel(User user) {
        this.name = user.getName();
        this.topics = AllTopicModel.convertEntityInModel(user.getTopics());
        this.massages = PresentMassModel.convertEntityInModel(user.getMassages());
    }

    public static List<PresentUserModel> convertEntityInModel(List<User> list){
        return list.stream()
                .map(user -> {
                    PresentUserModel presentUserModel = new PresentUserModel();
                    presentUserModel.setName(user.getName());
                    presentUserModel.setTopics(AllTopicModel.convertEntityInModel(user.getTopics()));
                    presentUserModel.setMassages(PresentMassModel.convertEntityInModel(user.getMassages()));
                    return presentUserModel;
                })
                .collect(Collectors.toList());
    }

}
