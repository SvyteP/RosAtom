package com.example.rosatom.serviceImpl;

import com.example.rosatom.customMethods.CheckMethods;
import com.example.rosatom.entity.Massage;
import com.example.rosatom.entity.Topic;
import com.example.rosatom.entity.User;
import com.example.rosatom.exception.NotFoundExeption;
import com.example.rosatom.exception.OnlyMessCannotBeDelException;
import com.example.rosatom.model.AddMassModel;
import com.example.rosatom.model.DelMassModel;
import com.example.rosatom.model.MassageModel;
import com.example.rosatom.model.PresentMassModel;
import com.example.rosatom.repository.MassageRepository;
import com.example.rosatom.repository.TopicRepository;
import com.example.rosatom.repository.UserRepository;
import com.example.rosatom.service.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void removeMassage(DelMassModel massageModel) throws Exception {
        try {
                Topic topic = topicRepository.findById(massageModel.getTopicId()).orElseThrow();
                if (topic.getTopic_mass().size()>1) {
                    Massage massage = massageRepository.findById(massageModel.getMassageId()).orElseThrow();
                    User user = userRepository.findById(massageModel.getUserId()).orElseThrow();

                    if (!CheckMethods.checkMassage(topic, massage, user)) {
                        throw new NotFoundExeption("Message not found / User is not the creator of the message");
                    } else {
                        user.getMassages().remove(massage);
                        topic.getTopic_mass().remove(massage);
                        massageRepository.deleteById(massageModel.getMassageId());
                        userRepository.save(user);
                        topicRepository.save(topic);
                    }
                }
                else
                {
                    throw  new OnlyMessCannotBeDelException("The last message in the topic cannot be deleted!");
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
               throw new NotFoundExeption("The specified user is not the post creator or administrator");
           }
        }
        catch (Exception e){
            throw new NotFoundExeption(e.getMessage());
        }

    }

    @Override
    public void addMassage(AddMassModel addMassModel) throws Exception {
        try {
                Topic topic = topicRepository.findById(addMassModel.getTopicId()).orElseThrow();
                User user = userRepository.findById(addMassModel.getUserId()).orElseThrow();
                Massage massage = new Massage(addMassModel.getBody(),user,topic);
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

    @Override
    public PresentMassModel readMassage(Long massageId) throws Exception {
        try {
            return new PresentMassModel(massageRepository.findById(massageId).orElseThrow());
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
    @Override
    public List<PresentMassModel> readAllMassage() throws Exception {
        try {
            return  PresentMassModel.convertEntityInModel((List<Massage>) massageRepository.findAll());
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
