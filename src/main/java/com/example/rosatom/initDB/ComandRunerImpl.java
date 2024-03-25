package com.example.rosatom.initDB;

import com.example.rosatom.entity.Massage;
import com.example.rosatom.entity.Topic;
import com.example.rosatom.entity.User;
import com.example.rosatom.model.AddTopicModel;
import com.example.rosatom.repository.UserRepository;
import com.example.rosatom.service.MassageService;
import com.example.rosatom.service.TopicService;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ComandRunerImpl implements CommandLineRunner {
    private final TopicService topicService;
    private final MassageService massageService;

    private final UserRepository userRepository;

    public ComandRunerImpl(TopicService topicService, MassageService massageService, UserRepository userRepository) {
        this.topicService = topicService;
        this.massageService = massageService;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        User user;
        Topic topic;
        Massage massage;
        AddTopicModel topicModel;

        user = new User("Petya");
        userRepository.save(user);
        user = new User("Vasya");
        userRepository.save(user);
        user = new User("Gosha");
        userRepository.save(user);

        topicModel = new AddTopicModel("There was a cool text here, but my laptop crashed (((",
                "I was sitting there writing, not touching anyone, and then БаЦ бац Бац баЦ and a blue screen",1L);
        topicService.createNewTopic(topicModel);
        topicModel = new AddTopicModel("How to write a Rest API if I don’t know how your spring works?",
                "Okay, let’s get some help from the guides :)",2L);
        topicService.createNewTopic(topicModel);
        topicModel = new AddTopicModel("Help me, I can't take it anymore!!",
                "IIt's been the 10th hour since I've been hitting the wall and I can't fix the error," +
                        " the function is running, but when I add a condition it stops working :(",3L);
        topicService.createNewTopic(topicModel);



    }
}
