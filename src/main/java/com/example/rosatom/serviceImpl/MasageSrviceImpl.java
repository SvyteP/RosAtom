package com.example.rosatom.serviceImpl;

import com.example.rosatom.customMethods.CheckMethods;
import com.example.rosatom.entity.Massage;
import com.example.rosatom.entity.Topic;
import com.example.rosatom.entity.User;
import com.example.rosatom.exception.NotFoundExeption;
import com.example.rosatom.model.MassageModel;
import com.example.rosatom.repository.MassageRepository;
import com.example.rosatom.repository.TopicRepository;
import com.example.rosatom.repository.UserRepository;
import com.example.rosatom.service.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasageSrviceImpl implements MassageService {
    MassageRepository massageRepository;
    TopicRepository topicRepository;
    UserRepository userRepository;
    @Autowired
    public MasageSrviceImpl(MassageRepository massageRepository, TopicRepository topicRepository, UserRepository userRepository) {
        this.massageRepository = massageRepository;
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
    }
    @Override
    public void removeMassage(MassageModel massageModel) throws Exception {
        try {
            if (CheckMethods.checkExistAll(massageModel)){
                Topic topic = topicRepository.findById(massageModel.getTopicId()).get();
                Massage massage = massageRepository.findById(massageModel.getMassageId()).get();
                User user = userRepository.findById(massageModel.getUserId()).get();

                if(CheckMethods.checkMassage(topic,massage,user)){
                    massageRepository.delete(massage);
                    massageRepository.save(massage);

                }

            }
            else {
                throw new NotFoundExeption("Указанные данные не найдены!");
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editMassage(MassageModel massageModel) {

    }

    @Override
    public void addMassage(MassageModel massageModel) throws Exception {
        try {
            if (CheckMethods.checkExistAddMass(massageModel)){
                Massage massage = massageModel.getMassage();

                Topic topic = topicRepository.findById(massageModel.getTopicId()).get();
                User user = userRepository.findById(massageModel.getUserId()).get();

                    topic.getTopic_mass().add(massage);
                    user.getMassages().add(massage);
                    massage.setUsers(user);
                    massage.setTopic(topic);

                    userRepository.save(user);
                    topicRepository.save(topic);
                    massageRepository.save(massage);



            }
            else {
                throw new NotFoundExeption("Указанные данные не найдены!");
            }
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
