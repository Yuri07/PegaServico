package com.compsol.appsol.pegaservico.firebase.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FirebaseModule.class, PegaServicoAppModule.class})
public interface FirebaseComponent {
}
