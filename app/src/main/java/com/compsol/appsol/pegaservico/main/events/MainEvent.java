package com.compsol.appsol.pegaservico.main.events;

import com.compsol.appsol.pegaservico.entities.User;

public class MainEvent {

    private int eventType;

    private User loggedUser;

    private String errorMessage;

    public final static int READ_DRIVER_EVENT = 0;

    public final static int onSuccessToRecoverSession = 10;
    public final static int onFailedToRecoverSession = 11;

    public final static int onSucceessToSaveFirebaseTokenInServer = 14;
    public final static int onFailedToSaveFirebaseTokenInServer = 15;

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /*public String getLoggedUserEmail() {
        return loggedUserEmail;
    }

    public void setLoggedUserEmail(String loggedUserEmail) {
        this.loggedUserEmail = loggedUserEmail;
    }*/

}
