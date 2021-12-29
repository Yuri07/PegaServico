package com.compsol.appsol.pegaservico.pegar;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.firebase.FirebaseChildEventListenerCallback;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.oferecer.events.OferecerEvent;
import com.compsol.appsol.pegaservico.pegar.events.PegarEvent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class PegarRepositoryImpl implements PegarRepository {

    private FirebaseAPI firebase;
    private EventBus eventBus;


    public PegarRepositoryImpl(FirebaseAPI firebase, EventBus eventBus) {
        this.firebase = firebase;
        this.eventBus = eventBus;

    }

    @Override
    public void subscribeForOfferedServicesUpdates() {
        firebase.subscribeForOfferedServicesUpdates(new FirebaseChildEventListenerCallback(){

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
    public void unsubscribeForOfferedServicesUpdates() {
        firebase.unsubscribeForOfferedServicesUpdates();
    }

    private void post(int type, ServiceItem serviceItem){
        post(type, serviceItem, null);
    }

    private void post(int type, String error){
        post(type, null,error);
    }

    private void post(int type, ServiceItem serviceItem, String error){
        PegarEvent pegarEvent = new PegarEvent();
        pegarEvent.setServiceItem(serviceItem);
        pegarEvent.setEventType(type);
        pegarEvent.setError(error);
        eventBus.post(pegarEvent);
    }

}
