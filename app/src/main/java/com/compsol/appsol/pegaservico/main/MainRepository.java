package com.compsol.appsol.pegaservico.main;

public interface MainRepository {

    void logout();
    void checkForSession();
    void changeUserConnectionStatus(int status);
    //void uploadCompletedTravelStatus(int status);
    //void getMyCar();
    //void sendFirebaseNotificationTokenToServer(String firebaseNotificationToken);

    void verifyToken();

}
