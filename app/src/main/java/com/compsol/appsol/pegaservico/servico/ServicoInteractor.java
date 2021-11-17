package com.compsol.appsol.pegaservico.servico;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

public interface ServicoInteractor {

    //void retrieveDataUser();

    void subscribeForDataUser();

    void unSubscribeForDataUser();

    void addService(ServiceItem service);
}
