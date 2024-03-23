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



public class CheckMethods {
    public static boolean checkMassage(Topic topic , Massage massage, User user){

        if (topic.getTopic_mass().stream().anyMatch(Massage ->Massage.equals(massage)) &&
                user.getMassages().stream().anyMatch(Massage->Massage.equals(massage)))
        {
            return true;
        }
        return false;
    }
}
