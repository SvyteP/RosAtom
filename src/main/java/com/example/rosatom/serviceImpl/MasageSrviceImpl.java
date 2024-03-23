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

import java.util.Date;

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
                Topic topic = topicRepository.findById(massageModel.getTopicId()).orElseThrow();
                Massage massage = massageRepository.findById(massageModel.getMassageId()).orElseThrow();
                User user = userRepository.findById(massageModel.getUserId()).orElseThrow();

                if(!CheckMethods.checkMassage(topic,massage,user)){
                    throw new NotFoundExeption("Сообщение не найдено / Пользователь не является создателем сообщения");
                }
                else {
                    user.getMassages().remove(massage);
                    topic.getTopic_mass().remove(massage);
                    massageRepository.deleteById(massageModel.getMassageId());
                    userRepository.save(user);
                    topicRepository.save(topic);
                }

        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void editMassage(MassageModel massageModel) throws NotFoundExeption {
        try
        {
            User user = userRepository.findById(massageModel.getUserId()).orElseThrow();
            Topic topic = topicRepository.findById(massageModel.getTopicId()).orElseThrow();
            Massage massage = massageRepository.findById(massageModel.getMassageId()).orElseThrow();
           if (CheckMethods.checkMassage(topic,massage,user)){
               massage.setBody(massageModel.getBody());
               massageRepository.save(massage);
           }
           else{
               //Здесь добавить throw new Exception
           }
        }
        catch (Exception e){
            throw new NotFoundExeption(e.getMessage());
        }

    }

    @Override
    public void addMassage(MassageModel addMassModel) throws Exception {
        try {

                Massage massage = new Massage();
                massage.setDate(new Date());
                massage.setBody(addMassModel.getBody());
                Topic topic = topicRepository.findById(addMassModel.getTopicId()).orElseThrow();
                User user = userRepository.findById(addMassModel.getUserId()).orElseThrow();
                massage.setUsers(user);
                massage.setTopic(topic);

                massageRepository.save(massage);

                topic.getTopic_mass().add(massage);
                user.getMassages().add(massage);

                userRepository.save(user);
                topicRepository.save(topic);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
