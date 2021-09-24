package com.compsol.appsol.pegaservico;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Provides;

public class PegaServicoAppModule {
    Application application;

    public PegaServicoAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }

}
