package com.compsol.appsol.pegaservico;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PegaServicoAppModule {

    Application application;
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";

    public PegaServicoAppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application){
        return application.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Application providesApplication(){
        return application;
    }

}
