package com.compsol.appsol.pegaservico.oferecer;

public class OferecerInteractorImpl implements OferecerInteractor {

    OferecerRepository oferecerRepository;

    public OferecerInteractorImpl(OferecerRepository repository) {
        oferecerRepository = repository;
    }

    @Override
    public void subscribeForMyServicesOfferedUpates() {
        oferecerRepository.subscribeForMyServicesOfferedUpates();
    }

    @Override
    public void unSubscribeForMyServicesOfferedUpates() {
        oferecerRepository.unSubscribeForMyServicesOfferedUpates();
    }
}
