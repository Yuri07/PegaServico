package com.compsol.appsol.pegaservico;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.compsol.appsol.pegaservico.firebase.di.FirebaseModule;
import com.compsol.appsol.pegaservico.lib.di.LibsModule;
import com.compsol.appsol.pegaservico.login.di.LoginComponent;
import com.compsol.appsol.pegaservico.login.di.LoginModule;
import com.compsol.appsol.pegaservico.login.ui.LoginView;
import com.compsol.appsol.pegaservico.main.di.MainComponent;
import com.compsol.appsol.pegaservico.main.di.MainModule;
import com.compsol.appsol.pegaservico.main.ui.MainView;

public class PegaServicoApp extends Application {

    public final static String EMAIL_KEY = "email";
    public final static String NOME_KEY = "nome";
    public final static String URL_PHOTO_USER_KEY = "urlPhotoUser";
    public final static String AVERAGE_RATINGS_PASSENGER_KEY = "averageRatingsPassenger";

    private LibsModule libsModule;
    private FirebaseModule firebaseModule;
    private PegaServicoAppModule pegaServicoAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initModules();
    }

    private void initModules() {
        libsModule = new LibsModule();
        firebaseModule = new FirebaseModule();
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

    public MainComponent getMainComponent(Context context, MainView view,
                                          FragmentManager manager, Fragment[] fragments) {
        libsModule.setContext(context);

        return null;/*DaggerMainComponent
                .builder()
                .taxiLivreAppModule(pegaServicoAppModule)
                .domainModule(firebaseModule)
                .libsModule(libsModule)
                .mainModule(new MainModule(view, manager, mapFragment))
                .build();*/
    }

}