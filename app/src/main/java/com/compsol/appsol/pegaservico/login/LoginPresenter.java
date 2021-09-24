package com.compsol.appsol.pegaservico.login;

import com.compsol.appsol.pegaservico.login.events.LoginEvent;

public interface LoginPresenter {

    void onCreate();
    void onDestroy();
    void onEventMainThread(LoginEvent event);
    void login(String email, String password);
    void registerNewUser(String email, String password);

}
