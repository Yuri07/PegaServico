package com.compsol.appsol.pegaservico.servico;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.servico.events.ServicoActivityEvent;

public interface ServicoPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void addService(ServiceItem service);
    //void retrieveDataUser();
    void onEventMainThread(ServicoActivityEvent event);

}
