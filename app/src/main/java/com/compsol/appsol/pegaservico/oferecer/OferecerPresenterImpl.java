package com.compsol.appsol.pegaservico.oferecer;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.oferecer.events.OferecerEvent;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerView;

import org.greenrobot.eventbus.Subscribe;

public class OferecerPresenterImpl implements OferecerPresenter {

    EventBus eventBus;
    OferecerView oferecerView;
    OferecerInteractor oferecerInteractor;


    public OferecerPresenterImpl(EventBus eventBus, OferecerView oferecerView, OferecerInteractor oferecerInteractor) {
        super();
        this.eventBus = eventBus;
        this.oferecerView = oferecerView;
        this.oferecerInteractor = oferecerInteractor;
    }

    @Override
    public void subscribeForMyServicesOfferedUpdates() {
        oferecerInteractor.subscribeForMyServicesOfferedUpates();
    }

    @Override
    public void unsubscribeForMyServicesOfferedUpdates() {
        oferecerInteractor.unSubscribeForMyServicesOfferedUpates();
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
    public void onEventMainThread(OferecerEvent event) {
        if (oferecerView != null) {
            if (event.getEventType() == OferecerEvent.READ_EVENT) {
                ServiceItem serviceItem = event.getServiceItem();
                oferecerView.onServiceReceived(serviceItem);
            }else if(event.getEventType() == OferecerEvent.ERROR_EVENT){
                oferecerView.onServiceReceivedError(event.getError());
            }
        }
    }


}
