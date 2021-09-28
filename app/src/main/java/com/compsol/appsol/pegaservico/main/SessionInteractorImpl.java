package com.compsol.appsol.pegaservico.main;

public class SessionInteractorImpl implements SessionInteractor{

    MainRepository mainRepository;

    public SessionInteractorImpl(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    @Override
    public void logout() {
        mainRepository.logout();
    }

    @Override
    public void checkForSession() {
        mainRepository.checkForSession();
    }

    @Override
    public void changeConnectionStatus(int status) {
        mainRepository.changeUserConnectionStatus(status);
    }
}
