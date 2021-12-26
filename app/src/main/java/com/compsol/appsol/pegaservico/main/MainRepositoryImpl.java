package com.compsol.appsol.pegaservico.main;

import android.util.Log;

import androidx.annotation.NonNull;

import com.compsol.appsol.pegaservico.entities.User;
import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.firebase.FirebaseActionListenerCallback;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.main.events.MainEvent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainRepositoryImpl implements MainRepository{

    private EventBus eventBus;
    private FirebaseAPI firebase;

    private DatabaseReference myUserReference;
    //private Driver loggedDriver;

    public MainRepositoryImpl(EventBus eventBus, FirebaseAPI firebase) {
        this.eventBus = eventBus;
        this.firebase = firebase;

        myUserReference = firebase.getMyUserReference();
        //loggedDriver = new Driver();
    }

    @Override
    public void logout() {
        if(firebase!=null)
            firebase.logout();
    }

    @Override
    public void checkForSession() {
        firebase.checkForSession(new FirebaseActionListenerCallback(){
            @Override
            public void onSuccess() {

                myUserReference = firebase.getMyUserReference();//pega a referencia no
                // database para o usuario atualmente autenticado
                myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        initSignIn(snapshot);
                    }
                    @Override
                    public void onCancelled(DatabaseError firebaseError) {
                        post(MainEvent.onFailedToRecoverSession, firebaseError.getMessage());
                        //Log.d("d", "Nao conseguio ler na referencia para meu usuario: " + firebaseError.getMessage());
                    }
                });
            }

            @Override
            public void onError(DatabaseError error) {
                //Log.d("d", "Nao existe sessao aberta no servidor: metodo post");
                post(MainEvent.onFailedToRecoverSession, "Falha em recuperar sessao");
            }

            @Override
            public void onError(Exception e) {
                post(MainEvent.onFailedToRecoverSession, "Falha em recuperar sessao");
            }

            @Override
            public void onError() {
                post(MainEvent.onFailedToRecoverSession, "Falha em recuperar sessao");
            }
        });
    }

    @Override
    public void changeUserConnectionStatus(int status) {
        firebase.changeUserConnectionStatus(status);
    }

    @Override
    public void verifyToken() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("d", "Fetching FCM registration token failed",
                                    task.getException());
                            post(MainEvent.onFailedToSaveFirebaseTokenInServer,
                                    "getToken() Task dont complete!");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        sendFirebaseNotificationTokenToServer(token);

                        // Log and toast
                        //String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("d", "FirebaseMessaging.getToken()");
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void sendFirebaseNotificationTokenToServer(String firebaseNotificationToken) {
        firebase.sendTokenToServer(firebaseNotificationToken, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(MainEvent.onSucceessToSaveFirebaseTokenInServer);
            }

            @Override
            public void onError(DatabaseError error) {
                post(MainEvent.onFailedToSaveFirebaseTokenInServer, "Usuario nulo");
            }

            @Override
            public void onError(Exception e) {

            }

            @Override
            public void onError() {

            }
        });

    }

    private void initSignIn(DataSnapshot snapshot){
        User currentUser = snapshot.getValue(User.class);//null caso seja a primeira vez que o metodo e executado para esse usuario
        if (currentUser == null) {
            currentUser = new User(firebase.getAuthUserEmail(), 1, null);
            registerNewUser(currentUser);
        }
        firebase.changeUserConnectionStatus(User.ONLINE);
        post(MainEvent.onSuccessToRecoverSession, null, currentUser);

    }

    private void registerNewUser(User newUser) {
        if (newUser.getEmail()!= null) {
            myUserReference.setValue(newUser);
        }
    }

    private void post(int type){
        post(type, null, null);
    }

    private void post(int type, String errorMessage) {
        post(type, errorMessage, null);
    }

    private void post(int type, User loggedUser){
        post(type, null, loggedUser);
    }

    /*private void post(int type, Car myCar) {
        post(type, null, null);
    }*/

    private void post(int type, String errorMessage, User loggedUser){
        MainEvent mainEvent = new MainEvent();
        mainEvent.setEventType(type);
        if (errorMessage != null) {
            mainEvent.setErrorMessage(errorMessage);
        }
        mainEvent.setLoggedUser(loggedUser);
        //mainEvent.setMyCar(myCar);
        eventBus.post(mainEvent);
    }

}
