package com.compsol.appsol.pegaservico.main.di;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.main.MainInteractor;
import com.compsol.appsol.pegaservico.main.MainInteractorImpl;
import com.compsol.appsol.pegaservico.main.MainPresenter;
import com.compsol.appsol.pegaservico.main.MainPresenterImpl;
import com.compsol.appsol.pegaservico.main.MainRepository;
import com.compsol.appsol.pegaservico.main.MainRepositoryImpl;
import com.compsol.appsol.pegaservico.main.SessionInteractor;
import com.compsol.appsol.pegaservico.main.SessionInteractorImpl;
import com.compsol.appsol.pegaservico.main.ui.MainView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private MainView view;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    @Provides
    @Singleton
    MainView providesMainView(){
        return this.view;
    }

    @Provides @Singleton
    Fragment[] providesFragmentArray(){
        return this.fragments;
    }

    @Provides
    @Singleton
    FragmentManager providesFragmentManager(){
        return fragmentManager;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(EventBus eventBus, MainView mainView, MainInteractor mainInteractor, SessionInteractor sessionInteractor) {
        return new MainPresenterImpl(eventBus, mainView, mainInteractor, sessionInteractor);
    }

    @Provides @Singleton
    MainInteractor providesMainInteractor(MainRepository repository) {
        return new MainInteractorImpl(repository);
    }

    @Provides @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository) {
        return new SessionInteractorImpl(repository);
    }

    @Provides @Singleton
    MainRepository providesMainRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new MainRepositoryImpl(eventBus, firebase);
    }

}
