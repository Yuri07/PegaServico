package com.compsol.appsol.pegaservico.main.ui;

import com.compsol.appsol.pegaservico.entities.User;

public interface MainView {

    void setUIVisibility(boolean enabled);

    //void checkForSession();
    void navigateToLoginScreen();
    void setLoggedUser(User loggedUser);

    void logout();

    void onSucceessToSaveFirebaseTokenInServer();
    void onFailedToSaveFirebaseTokenInServer(String errorMessage);

}
