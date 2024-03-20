package com.example.rosatom.service;

import com.example.rosatom.model.MassageModel;

public interface MassageService {

    public void removeMassage(MassageModel actionMassage) throws Exception;

    public void editMassage(MassageModel actionMassage);

    public void addMassage(MassageModel actionMassage) throws Exception;
}
