package com.compsol.appsol.pegaservico.login.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.R;
import com.compsol.appsol.pegaservico.login.LoginPresenter;
import com.compsol.appsol.pegaservico.login.di.LoginComponent;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {

    @BindView(R.id.editTxtEmail)
    EditText editTxtEmail;
    @BindView(R.id.editTxtPassword)
    EditText editTxtPassword;
    @BindView(R.id.btnSignin)
    Button btnSignin;
    @BindView(R.id.btnSignup)
    Button btnSignup;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.layoutMainContainer)
    RelativeLayout layoutMainContainer;

    @Inject
    LoginPresenter presenter;
    @Inject
    SharedPreferences sharedPreferences;

    private PegaServicoApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("d", "LoginActivityonCreate() " );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        app = (PegaServicoApp) getApplication();

        setupInjection();
        presenter.onCreate();

    }

    private void setupInjection() {
        LoginComponent loginComponent = app.getLoginComponent(this);
        loginComponent.inject(this);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    @OnClick(R.id.btnSignup)
    public void handleSignUp() {
        presenter.registerNewUser(editTxtEmail.getText().toString(),
                editTxtPassword.getText().toString());
    }

    @Override
    @OnClick(R.id.btnSignin)
    public void handleSignIn() {
        presenter.login(editTxtEmail.getText().toString(),
                editTxtPassword.getText().toString());
    }

    @Override
    public void newUserSuccess() {
        Snackbar.make(layoutMainContainer, R.string.login_notice_message_useradded, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToMainScreen() {
        //startActivity(new Intent(this, MainActivity.class));
        Log.d("d", "LoginActivity.navigateToMainScreen() " );
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void setUserEmail(String email) {
        if (email != null) {
            String key = TaxiLivreDriverApp.EMAIL_KEY;//app.getEmailKey();
            sharedPreferences.edit().putString(key, email).apply();//.commit();//commit() e o que tem no codigo original lesson4.edx
        }
    }

    @Override
    public void loginError(String error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signin), error);
        editTxtPassword.setError(msgError);
    }

    @Override
    public void newUserError(String error) {
        editTxtPassword.setText("");
        String msgError = String.format(getString(R.string.login_error_message_signup), error);
        editTxtPassword.setError(msgError);
    }

    private void setInputs(boolean enabled) {
        btnSignin.setEnabled(enabled);
        btnSignup.setEnabled(enabled);
        editTxtEmail.setEnabled(enabled);
        editTxtPassword.setEnabled(enabled);
    }
}
