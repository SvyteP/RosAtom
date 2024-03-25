package com.example.rosatom.service;

import com.example.rosatom.exception.NotFoundExeption;
import com.example.rosatom.model.*;

import java.util.List;

public interface TopicService {
     void createNewTopic(AddTopicModel topicModel) throws Exception;
     void removeTopic(DelTopicModel topicModel) throws NotFoundExeption;
     PresentTopicModel readAllMassage(Long topicId) throws Exception;
     void editTopic(EditTopicModel topicModel) throws Exception;
     List<AllTopicModel> readAllTopics() throws Exception;
}
