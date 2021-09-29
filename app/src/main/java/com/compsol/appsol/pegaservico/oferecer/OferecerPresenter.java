package com.compsol.appsol.pegaservico.oferecer;

import com.compsol.appsol.pegaservico.oferecer.events.OferecerEvent;

public interface OferecerPresenter {

    void onCreate();
    void onDestroy();

    void getMyOfferServices(String email);

    void onEventMainThread(OferecerEvent event);

}
