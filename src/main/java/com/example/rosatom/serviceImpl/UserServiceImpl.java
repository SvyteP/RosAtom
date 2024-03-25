package com.example.rosatom.serviceImpl;

import com.example.rosatom.entity.User;
import com.example.rosatom.model.PresentUserModel;
import com.example.rosatom.model.UserModel;
import com.example.rosatom.repository.UserRepository;
import com.example.rosatom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private  final UserRepository userRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(String name) throws Exception {
        try {
            User user = new User(name);
            userRepository.save(user);

        }
        catch (Exception e)
        {
            throw  new Exception(e.getMessage());
        }
    }
    @Override
    public List<PresentUserModel> readAllUsers() throws Exception {
        try {
            return PresentUserModel.convertEntityInModel( (List<User>) userRepository.findAll()) ;
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
    @Override
    public void deleteUser(Long userId) throws Exception {
        try {
            userRepository.deleteById(userId);
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
    @Override
    public void editUser(UserModel userModel) throws Exception {
        try {
            User user = userRepository.findById(userModel.getUserId()).orElseThrow();
            user.setName(userModel.getName());
            userRepository.save(user);
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
    @Override
    public PresentUserModel readUser (Long userId) throws Exception {
        try {
           return new PresentUserModel(userRepository.findById(userId).orElseThrow());
        }
        catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }

}
