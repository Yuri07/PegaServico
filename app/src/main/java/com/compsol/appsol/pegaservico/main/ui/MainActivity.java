package com.compsol.appsol.pegaservico.main.ui;

import android.os.Bundle;

import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.entities.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.compsol.appsol.pegaservico.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements MainView {

    private ActivityMainBinding binding;

    public static final String RECEIVER_INTENT = "RECEIVER_INTENT";

    public static final String FILTRO_KEY = "MainActivity_KEY";
    public static final String MENSAGEM_KEY = "MainActivity_MENSAGEM_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



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
}