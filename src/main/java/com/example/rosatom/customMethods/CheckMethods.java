package com.example.rosatom.customMethods;


import com.example.rosatom.entity.Massage;
import com.example.rosatom.entity.Topic;
import com.example.rosatom.entity.User;
import com.example.rosatom.model.MassageModel;
import com.example.rosatom.repository.MassageRepository;
import com.example.rosatom.repository.TopicRepository;
import com.example.rosatom.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CheckMethods {

    private static UserRepository userRepository;
    private static TopicRepository topicRepository;
    private static MassageRepository massageRepository;

    @Autowired
    public CheckMethods(UserRepository userRepository, TopicRepository topicRepository, MassageRepository massageRepository) {
        this.userRepository = userRepository;
        this.topicRepository = topicRepository;
        this.massageRepository = massageRepository;
    }

    public static boolean checkExistAll(MassageModel massageModel){
        return userRepository.existsById(massageModel.getUserId()) ||
                topicRepository.existsById(massageModel.getTopicId()) ||
                massageRepository.existsById(massageModel.getMassageId());
    }
    public static boolean checkExistAddMass(MassageModel massageModel){
        return userRepository.existsById(massageModel.getUserId()) ||
                topicRepository.existsById(massageModel.getTopicId());
    }
    public static boolean checkMassage(Topic topic , Massage massage, User user){

        return topic.getTopic_mass().stream().anyMatch(Massage ->Massage.equals(massage)) &&
                user.getMassages().stream().anyMatch(Massage->Massage.equals(massage));
    }



}
