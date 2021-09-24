package com.compsol.appsol.pegaservico.login.di;

import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.login.LoginInteractor;
import com.compsol.appsol.pegaservico.login.LoginInteractorImpl;
import com.compsol.appsol.pegaservico.login.LoginPresenter;
import com.compsol.appsol.pegaservico.login.LoginPresenterImpl;
import com.compsol.appsol.pegaservico.login.LoginRepository;
import com.compsol.appsol.pegaservico.login.LoginRepositoryImpl;
import com.compsol.appsol.pegaservico.login.SignupInteractor;
import com.compsol.appsol.pegaservico.login.SignupInteractorImpl;
import com.compsol.appsol.pegaservico.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {

    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView(){
        return this.view;
    }

    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView,
                                          LoginInteractor loginInteractor,
                                          SignupInteractor signupInteractor) {
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor, signupInteractor);
    }

    @Provides @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository repository) {
        return new LoginInteractorImpl(repository);
    }

    @Provides @Singleton
    SignupInteractor providesSignupInteractor(LoginRepository repository) {
        return new SignupInteractorImpl(repository);
    }

    @Provides @Singleton
    LoginRepository providesLoginRepository(FirebaseAPI firebase, EventBus eventBus) {
        return new LoginRepositoryImpl(firebase, eventBus);
    }

}
