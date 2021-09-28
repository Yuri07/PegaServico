package com.compsol.appsol.pegaservico.main;

import com.compsol.appsol.pegaservico.main.events.MainEvent;

public interface MainPresenter {

    void onCreate();
    //void changeToOnlineStatus();
    //void changeToOfflineStatus();
    void onDestroy();
    void onEventMainThread(MainEvent event);

    /*void startWaitingTravel();
    void stopWaitingTravel();
    void startInTravelStatus();
    void uploadCompletedTravelStatus();*/

    void logout();
    void checkForSession();

    //void getMyCar();

    //void sendFirebaseNotificationTokenToServer(String firebaseNotificationToken);

    void verifyToken();

}
