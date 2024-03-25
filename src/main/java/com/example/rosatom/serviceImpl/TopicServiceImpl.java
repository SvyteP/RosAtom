package com.example.rosatom.serviceImpl;

import com.example.rosatom.entity.Massage;
import com.example.rosatom.entity.Topic;
import com.example.rosatom.entity.User;
import com.example.rosatom.exception.NotFoundExeption;
import com.example.rosatom.exception.TitleIsNotExistException;
import com.example.rosatom.model.*;
import com.example.rosatom.repository.MassageRepository;
import com.example.rosatom.repository.TopicRepository;
import com.example.rosatom.repository.UserRepository;
import com.example.rosatom.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicServiceImpl implements TopicService {

    private final UserRepository userRepository;
    private final TopicRepository topicRepository;
    private final MassageRepository massageRepository;
    @Autowired
    public TopicServiceImpl(UserRepository userRepository, TopicRepository topicRepository, MassageRepository massageRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.massageRepository = massageRepository;
    }

    @Override
    public void createNewTopic(AddTopicModel topicModel ) throws Exception {
        try {
            if (!topicRepository.existsTopicByTitle(topicModel.getTitle())) {

                User user = userRepository.findById(topicModel.getUserId()).orElseThrow();

                Topic topic = new Topic(topicModel.getTitle(), user);

                Massage massage = new Massage(topicModel.getBody(), user, topic);


                topic.getTopic_mass().add(massage);
                user.getMassages().add(massage);
                user.getTopics().add(topic);

                userRepository.save(user);
            }
            else
            {
                throw new TitleIsNotExistException("The specified topic name already exists");
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void removeTopic(DelTopicModel topicModel) throws NotFoundExeption {
        try {
            Long userId = topicModel.getUserId();
            Long topicId = topicModel.getTopicId();
            if (!topicRepository.existsTopicByUsersIdAndId(userId, topicId))
            {
                throw new NotFoundExeption("The user is not the topic creator or administrator!");
            }
            else{
                topicRepository.deleteById(topicModel.getTopicId());
            }

        }
        catch (Exception e){
            throw new NotFoundExeption(e.getMessage());
        }

    }

    @Override
    public PresentTopicModel readAllMassage(Long topicId) throws Exception {
        try {
            Topic topic = topicRepository.findById(topicId).orElseThrow();
            PresentTopicModel presentTopicModel = new PresentTopicModel(topic.getTitle(),
                    PresentMassModel.convertEntityInModel(topic.getTopic_mass()));

            return presentTopicModel;
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public void editTopic(EditTopicModel editTopicModel) throws Exception {
        try {
            Topic topic = topicRepository.findById(editTopicModel.getTopicId()).orElseThrow();

            if (topicRepository.existsTopicByUsersIdAndId(editTopicModel.getUserId(), editTopicModel.getTopicId())){
                topic.setTitle(editTopicModel.getTitle());
                topicRepository.save(topic);
            }
            else {
                throw new NotFoundExeption("The user is not the topic creator or administrator!");
            }
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
    @Override
    public List<AllTopicModel> readAllTopics() throws Exception {
        try {
            return AllTopicModel.convertEntityInModel((List<Topic>) topicRepository.findAll());
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
