package com.compsol.appsol.pegaservico.main.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;
import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.lib.di.LibsModule;
import com.compsol.appsol.pegaservico.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class, FirebaseModule.class, LibsModule.class, PegaServicoAppModule.class})
public interface MainComponent {
    void inject(MainActivity activity);
}
