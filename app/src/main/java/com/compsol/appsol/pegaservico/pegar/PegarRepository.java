package com.compsol.appsol.pegaservico.pegar;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

public interface PegarRepository {

    void subscribeForOfferedServicesUpdates();
    void unsubscribeForOfferedServicesUpdates();

    void applyForService(ServiceItem service);
}
