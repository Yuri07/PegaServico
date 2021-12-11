package com.compsol.appsol.pegaservico.login.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;
import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.lib.di.LibsModule;
import com.compsol.appsol.pegaservico.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LoginModule.class, FirebaseModule.class, LibsModule.class, PegaServicoAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
