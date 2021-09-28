package com.compsol.appsol.pegaservico.main;

public class MainInteractorImpl implements MainInteractor{

    MainRepository mainRepository;

    public MainInteractorImpl(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public void setRecipient(String recipient) {

    }

    @Override
    public void verifyToken() {
        mainRepository.verifyToken();
    }
}
