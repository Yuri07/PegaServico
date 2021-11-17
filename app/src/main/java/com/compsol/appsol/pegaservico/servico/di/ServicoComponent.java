package com.compsol.appsol.pegaservico.servico.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;
import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.lib.di.LibsModule;
import com.compsol.appsol.pegaservico.servico.ui.ServicoActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ServicoModule.class, FirebaseModule.class, LibsModule.class, PegaServicoAppModule.class})
public interface ServicoComponent {
    void inject(ServicoActivity activity);
}
