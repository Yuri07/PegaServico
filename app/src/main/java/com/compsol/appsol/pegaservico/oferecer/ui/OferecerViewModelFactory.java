package com.compsol.appsol.pegaservico.oferecer.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.compsol.appsol.pegaservico.PegaServicoApp;

public class OferecerViewModelFactory implements ViewModelProvider.Factory {

    //private Application application;
    //private OferecerView view = null;
    private Fragment fragment = null;
    private PegaServicoApp app = null;


    public OferecerViewModelFactory(Fragment fragment, PegaServicoApp app) {
        //this.application = application;
        //this.view = view;
        this.fragment = fragment;
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new OferecerViewModel( fragment, app );
    }
}
