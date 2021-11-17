package com.compsol.appsol.pegaservico.servico;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.entities.User;
import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.firebase.FirebaseActionListenerCallback;
import com.compsol.appsol.pegaservico.firebase.FirebaseEventListenerCallback;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.servico.events.ServicoActivityEvent;
import com.compsol.appsol.pegaservico.servico.ui.ServicoActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ServicoRepositoryImpl implements ServicoRepository{

    private FirebaseAPI firebase;
    private EventBus eventBus;

    private DatabaseReference myUserReference;

    public ServicoRepositoryImpl(FirebaseAPI firebase, EventBus eventBus) {
        this.firebase = firebase;
        this.eventBus = eventBus;
    }

    @Override
    public void subscribeForDataUser() {
        firebase.subscribeForDataUser(new FirebaseEventListenerCallback(){

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                post(ServicoActivityEvent.onSuccessToGetDateUser, user);
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
                post(ServicoActivityEvent.onFailedToGetDateUser, error.getMessage());
            }
        });
    }

    @Override
    public void unSubscribeForDataUser() {
        firebase.unSubscribeForDataUser();
    }

    @Override
    public void addService(ServiceItem service) {
        String newServiceId = firebase.createServiceId();
        service.setServiceId(newServiceId);
        DatabaseReference servicesReference = firebase.getServicesReference();
        servicesReference.child(service.getServiceId()).setValue(service);
    }


    /*@Override
    public void retrieveDataUser() {
        firebase.checkForSession(new FirebaseActionListenerCallback(){
            @Override
            public void onSuccess() {

                myUserReference = firebase.getMyUserReference();//pega a referencia no
                // database para o usuario atualmente autenticado

                //myUserReference.keepSynced(true);

                myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        postOnSuccessRetrieveDataUser(snapshot);
                    }
                    @Override
                    public void onCancelled(DatabaseError firebaseError) {
                        post(firebaseError.getMessage());
                    }
                });
            }

            @Override
            public void onError(DatabaseError error) {
                post("Falha em recuperar a sessao");
            }
        });
    }*/

    private void postOnSuccessRetrieveDataUser(DataSnapshot snapshot){
        User currentUser = snapshot.getValue(User.class);//null caso seja a primeira vez que o metodo e executado para esse usuario
        post(ServicoActivityEvent.onSuccessToGetDateUser, currentUser);
    }

    private void post(int type, User currentUser){
        post(type, currentUser,null);
    }

    /*private void post(String error){
        post(-1,  null, error);
    }*/

    private void post(int type, String error){
        post(type, null, error);
    }

    private void post(int type, User currentUser, String error){
        ServicoActivityEvent event = new ServicoActivityEvent();
        event.setEventType(type);
        event.setCurrentUser(currentUser);
        event.setError(error);

        eventBus.post(event);
    }


}
