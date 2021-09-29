package com.compsol.appsol.pegaservico.main;

import com.compsol.appsol.pegaservico.entities.User;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.main.events.MainEvent;
import com.compsol.appsol.pegaservico.main.ui.MainView;

import org.greenrobot.eventbus.Subscribe;

public class MainPresenterImpl implements MainPresenter{

    EventBus eventBus;
    MainView mainView;
    MainInteractor mainInteractor;
    SessionInteractor sessionInteractor;

    public MainPresenterImpl(EventBus eventBus, MainView mainView, MainInteractor mainInteractor, SessionInteractor sessionInteractor) {
        this.eventBus = eventBus;
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
        this.sessionInteractor = sessionInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mainView = null;
        eventBus.unregister(this);
    }

    @Override
    public void logout() {
        sessionInteractor.changeConnectionStatus(User.OFFLINE);
        sessionInteractor.logout();
    }

    @Override
    public void checkForSession() {
        sessionInteractor.checkForSession();
    }

    @Override
    public void verifyToken() {
        mainInteractor.verifyToken();
    }

    @Override
    @Subscribe
    public void onEventMainThread(MainEvent event) {
        switch (event.getEventType()) {
            case MainEvent.onSuccessToRecoverSession:
                onSuccessToRecoverSession(event.getLoggedUser());
                break;
            case MainEvent.onFailedToRecoverSession:
                //Log.d("d", "onEventMainThread:onFailedToRecoverSession " );
                onFailedToRecoverSession();
                break;
            case MainEvent.onSucceessToSaveFirebaseTokenInServer:
                onSuccessToSaveFirebaseTokenInServer();
                break;
            case MainEvent.onFailedToSaveFirebaseTokenInServer:
                onFailedToSaveFirebaseTokenInServer(event.getErrorMessage());
                break;
        }
    }

    private void onSuccessToRecoverSession(User loggedUser) {
        if (mainView != null) {
            mainView.setLoggedUser(loggedUser);
            mainView.setUIVisibility(true);
        }
    }

    private void onFailedToRecoverSession() {
        if (mainView != null) {
            //Log.d("d", "onFailedToRecoverSession() " );
            mainView.navigateToLoginScreen();
        }
    }

    private void onSuccessToSaveFirebaseTokenInServer(){
        mainView.onSucceessToSaveFirebaseTokenInServer();
    }

    private void onFailedToSaveFirebaseTokenInServer(String errorMessage){
        mainView.onFailedToSaveFirebaseTokenInServer(errorMessage);
    }

}
