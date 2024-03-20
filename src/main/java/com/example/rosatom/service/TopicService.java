package com.example.rosatom.service;

import com.example.rosatom.entity.Topic;
import com.example.rosatom.model.TopicModel;

public interface TopicService {
    public void createNewTopic(Topic topic);
    public void removeTopic(Long topicId,Long userId);
    public void readAllMassage(Long topicId);

    /*  private void getAllTopics(){}*/
}
