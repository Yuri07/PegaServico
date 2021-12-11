package com.compsol.appsol.pegaservico.servico;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.entities.User;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.servico.events.ServicoActivityEvent;
import com.compsol.appsol.pegaservico.servico.ui.ServicoActivity;
import com.compsol.appsol.pegaservico.servico.ui.ServicoView;

import org.greenrobot.eventbus.Subscribe;

public class ServicoPresenterImpl implements ServicoPresenter{

    EventBus eventBus;
    ServicoView servicoView;
    ServicoInteractor servicoInteractor;

    public ServicoPresenterImpl(EventBus eventBus, ServicoView servicoView, ServicoInteractor servicoInteractor) {
        this.eventBus = eventBus;
        this.servicoView = servicoView;
        this.servicoInteractor = servicoInteractor;
    }

    @Override
    public void onPause() {
        servicoInteractor.unSubscribeForDataUser();
    }

    @Override
    public void onResume() {
        servicoInteractor.subscribeForDataUser();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        //servicoInteractor.destroyChatListener();
        servicoView = null;
    }

    @Override
    public void addService(ServiceItem servico) {
        servicoInteractor.addService(servico);
    }

    /*@Override
    public void retrieveDataUser() {
        servicoInteractor.retrieveDataUser();
    }*/

    @Override
    @Subscribe
    public void onEventMainThread(ServicoActivityEvent event) {
        User currentUser = event.getCurrentUser();
        if(currentUser!=null){
            servicoView.onSuccessToGetDataUser(event.getCurrentUser());
        }else {
            switch (event.getEventType()) {
                case ServicoActivityEvent.onServiceConfirmed:
                    onServiceConfirmed();
                    break;
                case ServicoActivityEvent.onServiceAddedError:
                    onServiceConfirmedError(event.getError());
                    break;
                case ServicoActivityEvent.onFailedToGetDateUser:
                    onFailedToGetDateUser(event.getError());
                    break;
            }
        }

    }



    private void onFailedToGetDateUser(String error) {
        if(servicoView!=null){
            servicoView.onFailedToGetDateUser(error);
        }
    }

    private void onServiceConfirmed() {
        if(servicoView!=null){
            servicoView.onServiceConfirmed();
        }
    }

    private void onServiceConfirmedError(String error) {
        if(servicoView!=null){
            servicoView.onServiceConfirmedError(error);
        }
    }



}
