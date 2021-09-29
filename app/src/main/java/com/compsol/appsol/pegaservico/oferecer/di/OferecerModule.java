package com.compsol.appsol.pegaservico.oferecer.di;

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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OferecerModule {

    OferecerView view;

    public OferecerModule(OferecerView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    OferecerView providesOferecerView() {
        return this.view;
    }

    @Provides
    @Singleton
    OferecerPresenter providesOferecerPresenter(EventBus eventBus, OferecerView view, OferecerInteractor listInteractor) {
        return new OferecerPresenterImpl(eventBus, view, listInteractor);
    }

    @Provides @Singleton
    OferecerInteractor providesOferecerInteractor(OferecerRepository repository) {
        return new OferecerInteractorImpl(repository);
    }

    @Provides @Singleton
    OferecerRepository providesOferecerRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new OferecerRepositoryImpl(firebase, eventBus);
    }

    @Provides @Singleton
    MyServiceListAdapter providesOferecerAdapter(List<ServiceItem> oferecerList, ImageLoader imageLoader) {
        return new MyServiceListAdapter(oferecerList, imageLoader);
    }

    @Provides @Singleton
    List<ServiceItem> providesOferecerList() {
        return new ArrayList<ServiceItem>();
    }
}
