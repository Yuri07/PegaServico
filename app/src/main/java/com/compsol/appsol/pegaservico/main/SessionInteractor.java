package com.compsol.appsol.pegaservico.main;

public interface SessionInteractor {

    void logout();
    void checkForSession();
    void changeConnectionStatus(int status);

}
