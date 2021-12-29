package com.compsol.appsol.pegaservico.pegar.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;
import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.lib.di.LibsModule;
import com.compsol.appsol.pegaservico.oferecer.di.OferecerModule;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerViewModel;
import com.compsol.appsol.pegaservico.pegar.ui.PegarViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {PegarModule.class, FirebaseModule.class, LibsModule.class, PegaServicoAppModule.class})
public interface PegarComponent {
    void inject(PegarViewModel pegarViewModel);
}
