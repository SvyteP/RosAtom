package com.example.rosatom.service;

import com.example.rosatom.exception.NotFoundExeption;
import com.example.rosatom.model.AddMassModel;
import com.example.rosatom.model.DelMassModel;
import com.example.rosatom.model.MassageModel;
import com.example.rosatom.model.PresentMassModel;

import java.util.List;

public interface MassageService {

    void removeMassage(DelMassModel massageModel) throws Exception;

    void editMassage(MassageModel massageModel) throws NotFoundExeption;

    void addMassage(AddMassModel addMassModel) throws Exception;
    PresentMassModel readMassage(Long massageId) throws Exception;

    List<PresentMassModel> readAllMassage() throws Exception;
}
