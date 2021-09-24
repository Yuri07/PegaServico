package com.compsol.appsol.pegaservico.firebaseservice.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;
import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.firebaseservice.MyFirebaseInstanceIDService;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FirebaseModule.class, PegaServicoAppModule.class})
public interface FIIDServiceComponent {
    void inject(MyFirebaseInstanceIDService  myFirebaseInstanceIDService);
}
