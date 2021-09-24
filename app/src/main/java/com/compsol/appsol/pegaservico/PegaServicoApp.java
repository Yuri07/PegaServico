package com.compsol.appsol.pegaservico;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.lib.di.LibsModule;
import com.compsol.appsol.pegaservico.login.di.LoginComponent;
import com.compsol.appsol.pegaservico.login.di.LoginModule;
import com.compsol.appsol.pegaservico.login.ui.LoginView;

public class PegaServicoApp extends Application {

    private LibsModule libsModule;
    private FirebaseModule domainModule;
    private PegaServicoAppModule pegaServicoAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initModules();
    }

    private void initModules() {
        libsModule = new LibsModule();
        domainModule = new FirebaseModule();
        pegaServicoAppModule = new PegaServicoAppModule(this);

    }

    public boolean getConectivityStatus(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return null;/*DaggerLoginComponent
                .builder()
                .taxiLivreDriverAppModule(pegaServicoAppModule)
                .domainModule(domainModule)
                .libsModule(libsModule)
                .loginModule(new LoginModule(view))
                .build();*/
    }

}