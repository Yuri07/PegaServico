package com.compsol.appsol.pegaservico.main.ui;

import android.content.Intent;
import android.os.Bundle;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.entities.User;
import com.compsol.appsol.pegaservico.login.ui.LoginActivity;
import com.compsol.appsol.pegaservico.main.MainPresenter;
import com.compsol.appsol.pegaservico.main.di.MainComponent;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerFragment;
import com.compsol.appsol.pegaservico.pegar.PegarFragment;
import com.compsol.appsol.pegaservico.perfil.PerfilFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.compsol.appsol.pegaservico.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainView {

    private ActivityMainBinding binding;

    public static final String RECEIVER_INTENT = "RECEIVER_INTENT";

    public final static int INTENT_REQUEST_CODE_ADD_SERVICO = 200;
    public final static int INTENT_RESULT_CODE_ADD_SERVICO_OK = 201;
    public final static int INTENT_RESULT_CODE_ADD_SERVICO_FAILED = 202;
    public final static int INTENT_RESULT_CODE_ADD_SERVICO_ON_BACK_BUTTON_PRESSED = 203;
    public static final String MESSAGE_SERVICO_ADDED_OK = "Serviço salvo com sucesso";
    public static final String MESSAGE_SERVICO_ADDED_FAILED = "Ocorreu uma falha ao tentar salvar o serviço";




    public static final String FILTRO_KEY = "MainActivity_KEY";
    public static final String MENSAGEM_KEY = "MainActivity_MENSAGEM_KEY";

    ConstraintLayout container;

    PegaServicoApp app = null;

    @Inject
    MainPresenter presenter;
    /*@Inject
    FragmentManager fragmentManager;
    @Inject
    Fragment[] fragments;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        app = (PegaServicoApp) getApplication();

        setupInjection();

        presenter.onCreate();
        presenter.checkForSession();


    }

    @Override
    public void setUIVisibility(boolean enabled) {

    }

    @Override
    public void navigateToLoginScreen() {

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    @Override
    public void setLoggedUser(User loggedUser) {
        setupMainScreen();
    }

    private void setupMainScreen() {

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        container = binding.containerMainActivity;

        setupBottomNavigationView();

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
        NavController navController = Navigation.findNavController(this,
                                                R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    private void setupInjection() {

        //Fragment[] fragments = new Fragment[]{new OferecerFragment(),
        //        new PegarFragment(), new PerfilFragment()};
        Fragment[] fragments = null;

        MainComponent mainComponent = app.getMainComponent(this, this,
                getSupportFragmentManager(), fragments);

        mainComponent.inject(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);//https://stackoverflow.com/questions/6147884/onactivityresult-is-not-being-called-in-fragment
        if(requestCode==INTENT_REQUEST_CODE_ADD_SERVICO ){
            if(resultCode==INTENT_RESULT_CODE_ADD_SERVICO_OK){
                Snackbar.make(container, MESSAGE_SERVICO_ADDED_OK, Snackbar.LENGTH_SHORT).show();
            }else if(resultCode==INTENT_RESULT_CODE_ADD_SERVICO_FAILED){
                    Snackbar.make(container, MESSAGE_SERVICO_ADDED_FAILED, Snackbar.LENGTH_SHORT).show();
            }
        }
    }

}