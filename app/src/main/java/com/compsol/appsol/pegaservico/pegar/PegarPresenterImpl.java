package com.compsol.appsol.pegaservico.pegar;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.oferecer.OferecerInteractor;
import com.compsol.appsol.pegaservico.oferecer.events.OferecerEvent;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerView;
import com.compsol.appsol.pegaservico.pegar.events.PegarEvent;
import com.compsol.appsol.pegaservico.pegar.ui.PegarView;

import org.greenrobot.eventbus.Subscribe;

public class PegarPresenterImpl implements PegarPresenter{

    EventBus eventBus;
    PegarView pegarView;
    PegarInteractor pegarInteractor;

    public PegarPresenterImpl(EventBus eventBus, PegarView pegarView, PegarInteractor pegarInteractor) {
        super();
        this.eventBus = eventBus;
        this.pegarView = pegarView;
        this.pegarInteractor = pegarInteractor;
    }

    @Override
    public void subscribeForOfferedServicesUpdates() {
        pegarInteractor.subscribeForOfferedServicesUpdates();
    }

    @Override
    public void unsubscribeForOfferedServicesUpdates() {
        pegarInteractor.unsubscribeForOfferedServicesUpdates();
    }

    @Override
    public void registerInEventBus() {
        eventBus.register(this);
    }

    @Override
    public void unregisterInEventBus() {
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventMainThread(PegarEvent event) {
        if (pegarView != null) {
            if (event.getEventType() == OferecerEvent.READ_EVENT) {
                ServiceItem serviceItem = event.getServiceItem();
                pegarView.onServiceReceived(serviceItem);
            }else if(event.getEventType() == OferecerEvent.ERROR_EVENT){
                pegarView.onServiceReceivedError(event.getError());
            }
        }
    }
}
