package com.compsol.appsol.pegaservico.main.ui;

import android.os.Bundle;
import android.view.MenuItem;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.entities.User;
import com.compsol.appsol.pegaservico.main.MainPresenter;
import com.compsol.appsol.pegaservico.main.di.MainComponent;
import com.compsol.appsol.pegaservico.oferecer.OferecerFragment;
import com.compsol.appsol.pegaservico.pegar.PegarFragment;
import com.compsol.appsol.pegaservico.perfil.PerfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.compsol.appsol.pegaservico.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    private ActivityMainBinding binding;

    public static final String RECEIVER_INTENT = "RECEIVER_INTENT";

    public static final String FILTRO_KEY = "MainActivity_KEY";
    public static final String MENSAGEM_KEY = "MainActivity_MENSAGEM_KEY";

    PegaServicoApp app = null;

    @Inject
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        app = (PegaServicoApp) getApplication();

        setupBottomNavigationView();

        setupInjection();



    }

    @Override
    public void setUIVisibility(boolean enabled) {

    }

    @Override
    public void navigateToLoginScreen() {

    }

    @Override
    public void setLoggedUser(User loggedUser) {

    }

    @Override
    public void logout() {

    }

    @Override
    public void onSucceessToSaveFirebaseTokenInServer() {

    }

    @Override
    public void onFailedToSaveFirebaseTokenInServer(String errorMessage) {

    }

    private void setupBottomNavigationView() {
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_oferecer, R.id.navigation_pegar, R.id.navigation_perfil)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void setupInjection() {

        Fragment[] fragments = new Fragment[]{new OferecerFragment(),
                new PegarFragment(), new PerfilFragment()};

        MainComponent mainComponent = app.getMainComponent(this, this, getSupportFragmentManager(), fragments);
        mainComponent.inject(this);

    }

}