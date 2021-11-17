package com.compsol.appsol.pegaservico.servico;

import com.compsol.appsol.pegaservico.entities.ServiceItem;

public class ServicoInteractorImpl implements ServicoInteractor{

    ServicoRepository servicoRepository;

    public ServicoInteractorImpl(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    /*@Override
    public void retrieveDataUser() {
        servicoRepository.retrieveDataUser();
    }*/

    @Override
    public void subscribeForDataUser() {
        servicoRepository.subscribeForDataUser();
    }

    @Override
    public void unSubscribeForDataUser() {
        servicoRepository.unSubscribeForDataUser();
    }

    @Override
    public void addService(ServiceItem service) {
        servicoRepository.addService(service);
    }
}
