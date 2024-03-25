package com.example.rosatom.service;

import com.example.rosatom.model.PresentUserModel;
import com.example.rosatom.model.UserModel;

import java.util.List;


public interface UserService {

     void createUser(String name) throws Exception;
     List<PresentUserModel> readAllUsers() throws Exception ;
     void deleteUser(Long userId) throws Exception;
     void editUser(UserModel userModel) throws Exception;
     PresentUserModel readUser (Long userId) throws Exception;

}
