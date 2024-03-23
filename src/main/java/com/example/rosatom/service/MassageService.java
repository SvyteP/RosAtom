package com.example.rosatom.service;

import com.example.rosatom.exception.NotFoundExeption;
import com.example.rosatom.model.MassageModel;

public interface MassageService {

    public void removeMassage(MassageModel massageModel) throws Exception;

    public void editMassage(MassageModel massageModel) throws NotFoundExeption;

    public void addMassage(MassageModel addMassModel) throws Exception;
}
