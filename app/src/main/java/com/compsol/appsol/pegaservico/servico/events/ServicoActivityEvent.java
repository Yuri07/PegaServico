package com.compsol.appsol.pegaservico.servico.events;

import com.compsol.appsol.pegaservico.entities.User;

public class ServicoActivityEvent {

    private int eventType;
    private String error;
    private User currentUser;

    public final static int onServiceConfirmed = 101;
    public final static int onServiceConfirmedError = 102;
    public final static int onSuccessToGetDateUser = 103;
    public final static int onFailedToGetDateUser = 104;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
