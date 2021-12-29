package com.compsol.appsol.pegaservico.pegar.events;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

public class PegarEvent {

    private ServiceItem serviceItem;
    private int eventType;
    private String error;

    public final static int ERROR_EVENT = 600;
    public final static int READ_EVENT = 601;

    public ServiceItem getServiceItem() {
        return serviceItem;
    }

    public void setServiceItem(ServiceItem serviceItem) {
        this.serviceItem = serviceItem;
    }

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

}
