package com.compsol.appsol.pegaservico.pegar.ui;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.compsol.appsol.pegaservico.PegaServicoApp;
import com.compsol.appsol.pegaservico.oferecer.ui.OferecerViewModel;

public class PegarViewModelFactory implements ViewModelProvider.Factory{

    private Fragment fragment = null;
    private PegaServicoApp app = null;

    public PegarViewModelFactory(Fragment fragment, PegaServicoApp app) {
        this.fragment = fragment;
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PegarViewModel( fragment, app );
    }

}
