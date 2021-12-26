package com.compsol.appsol.pegaservico.oferecer;

import com.compsol.appsol.pegaservico.oferecer.events.OferecerEvent;

public interface OferecerPresenter {

    void   subscribeForServicesOfferedUpdates();
    void unsubscribeForServicesOfferedUpdates();

    void registerInEventBus();
    void unregisterInEventBus();

    //void getMyOfferServices();

    void onEventMainThread(OferecerEvent event);

}
