package com.compsol.appsol.pegaservico.oferecer;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.firebase.FirebaseChildEventListenerCallback;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.oferecer.events.OferecerEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class OferecerRepositoryImpl implements OferecerRepository {

    private FirebaseAPI firebase;
    private EventBus eventBus;


    public OferecerRepositoryImpl(FirebaseAPI firebase, EventBus eventBus) {
        this.firebase = firebase;
        this.eventBus = eventBus;

    }

    @Override
    public void subscribeForServicesOfferedUpates() {
        firebase.subscribeForServicesOfferedUpates(new FirebaseChildEventListenerCallback(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                ServiceItem serviceItem = dataSnapshot.getValue(ServiceItem.class);

                post(OferecerEvent.READ_EVENT, serviceItem);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError error) {
                post(OferecerEvent.ERROR_EVENT, error.getMessage());
            }
        });
    }

    @Override
    public void unSubscribeForServicesOfferedUpates() {
        firebase.unSubscribeForChatUpdates();
    }

    private void post(int type, ServiceItem serviceItem){
        post(type, serviceItem, null);
    }

    private void post(int type, String error){
        post(type, null,error);
    }

    private void post(int type, ServiceItem serviceItem, String error){
        OferecerEvent oferecerEvent = new OferecerEvent();
        oferecerEvent.setServiceItem(serviceItem);
        oferecerEvent.setEventType(type);
        oferecerEvent.setError(error);
        eventBus.post(oferecerEvent);
    }

}
