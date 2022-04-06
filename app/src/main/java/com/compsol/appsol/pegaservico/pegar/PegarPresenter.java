package com.compsol.appsol.pegaservico.pegar;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.oferecer.events.OferecerEvent;
import com.compsol.appsol.pegaservico.pegar.events.PegarEvent;

public interface PegarPresenter {

    void subscribeForOfferedServicesUpdates();
    void unsubscribeForOfferedServicesUpdates();

    void registerInEventBus();
    void unregisterInEventBus();

    void applyForService(ServiceItem service);

    void onEventMainThread(PegarEvent event);


}
