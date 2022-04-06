package com.compsol.appsol.pegaservico.pegar;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.oferecer.OferecerRepository;

public class PegarInteractorImpl implements PegarInteractor{

    PegarRepository pegarRepository;

    public PegarInteractorImpl(PegarRepository repository) {
        pegarRepository = repository;
    }

    @Override
    public void subscribeForOfferedServicesUpdates() {
        pegarRepository.subscribeForOfferedServicesUpdates();
    }

    @Override
    public void unsubscribeForOfferedServicesUpdates() {
        pegarRepository.unsubscribeForOfferedServicesUpdates();
    }

    @Override
    public void applyForService(ServiceItem service) {
        pegarRepository.applyForService(service);
    }

}
