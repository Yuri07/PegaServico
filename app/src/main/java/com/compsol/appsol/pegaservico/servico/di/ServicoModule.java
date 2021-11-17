package com.compsol.appsol.pegaservico.servico.di;


import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.servico.ServicoInteractor;
import com.compsol.appsol.pegaservico.servico.ServicoInteractorImpl;
import com.compsol.appsol.pegaservico.servico.ServicoPresenter;
import com.compsol.appsol.pegaservico.servico.ServicoPresenterImpl;
import com.compsol.appsol.pegaservico.servico.ServicoRepository;
import com.compsol.appsol.pegaservico.servico.ServicoRepositoryImpl;
import com.compsol.appsol.pegaservico.servico.ui.ServicoView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ServicoModule {

    ServicoView view;

    public ServicoModule(ServicoView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    ServicoPresenter providesServicoPresenter(EventBus eventBus,
                                              ServicoView ServicoView,
                                              ServicoInteractor ServicoInteractor){
        return new ServicoPresenterImpl(eventBus,
                ServicoView,
                ServicoInteractor);
    }

    @Provides
    @Singleton
    ServicoInteractor providesServicoInteractor(ServicoRepository ServicoRepository){
        return new ServicoInteractorImpl(ServicoRepository);
    }

    @Provides
    @Singleton
    ServicoRepository providesServicoRepository(FirebaseAPI firebase, EventBus eventBus){
        return new ServicoRepositoryImpl(firebase, eventBus);
    }

}
