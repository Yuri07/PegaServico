package com.compsol.appsol.pegaservico.oferecer.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;
import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.lib.di.LibsModule;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerFragment;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {OferecerModule.class, FirebaseModule.class, LibsModule.class, PegaServicoAppModule.class})
public interface OferecerComponent {

    //void inject(OferecerFragment fragment);
    void inject(OferecerViewModel oferecerViewModel);

}
