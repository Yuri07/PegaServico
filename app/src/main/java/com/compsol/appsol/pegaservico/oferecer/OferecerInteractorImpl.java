package com.compsol.appsol.pegaservico.oferecer;

public class OferecerInteractorImpl implements OferecerInteractor {

    OferecerRepository oferecerRepository;

    public OferecerInteractorImpl(OferecerRepository repository) {
        oferecerRepository = repository;
    }

    @Override
    public void subscribeForServicesOfferedUpates() {
        oferecerRepository.subscribeForServicesOfferedUpates();
    }

    @Override
    public void unSubscribeForServicesOfferedUpates() {
        oferecerRepository.unSubscribeForServicesOfferedUpates();
    }
}
