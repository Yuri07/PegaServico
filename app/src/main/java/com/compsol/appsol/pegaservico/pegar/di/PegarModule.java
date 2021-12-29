package com.compsol.appsol.pegaservico.pegar.di;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.lib.base.ImageLoader;
import com.compsol.appsol.pegaservico.oferecer.OferecerInteractor;
import com.compsol.appsol.pegaservico.oferecer.OferecerInteractorImpl;
import com.compsol.appsol.pegaservico.oferecer.OferecerPresenter;
import com.compsol.appsol.pegaservico.oferecer.OferecerPresenterImpl;
import com.compsol.appsol.pegaservico.oferecer.OferecerRepository;
import com.compsol.appsol.pegaservico.oferecer.OferecerRepositoryImpl;
import com.compsol.appsol.pegaservico.oferecer.adapters.MyServiceListAdapter;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerView;
import com.compsol.appsol.pegaservico.pegar.PegarInteractor;
import com.compsol.appsol.pegaservico.pegar.PegarInteractorImpl;
import com.compsol.appsol.pegaservico.pegar.PegarPresenter;
import com.compsol.appsol.pegaservico.pegar.PegarPresenterImpl;
import com.compsol.appsol.pegaservico.pegar.PegarRepository;
import com.compsol.appsol.pegaservico.pegar.PegarRepositoryImpl;
import com.compsol.appsol.pegaservico.pegar.adapters.OfferedServicesListAdapter;
import com.compsol.appsol.pegaservico.pegar.ui.PegarView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PegarModule {

    PegarView view;

    public PegarModule(PegarView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    PegarView providesPegarView() {
        return this.view;
    }

    @Provides
    @Singleton
    PegarPresenter providesPegarPresenter(EventBus eventBus, PegarView view, PegarInteractor listInteractor) {
        return new PegarPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides @Singleton
    PegarInteractor providesPegarInteractor(PegarRepository repository) {
        return new PegarInteractorImpl(repository);
    }

    @Provides @Singleton
    PegarRepository providesPegarRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new PegarRepositoryImpl(firebase, eventBus);
    }

    @Provides @Singleton
    OfferedServicesListAdapter providesPegarAdapter(List<ServiceItem> pegarList, ImageLoader imageLoader) {
        return new OfferedServicesListAdapter(pegarList, imageLoader);
    }

    @Provides @Singleton
    List<ServiceItem> providesPegarList() {
        return new ArrayList<ServiceItem>();
    }

}
