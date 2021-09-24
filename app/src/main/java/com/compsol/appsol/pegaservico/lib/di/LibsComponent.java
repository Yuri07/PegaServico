package com.compsol.appsol.pegaservico.lib.di;

import com.compsol.appsol.pegaservico.PegaServicoAppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibsModule.class, PegaServicoAppModule.class})
public interface LibsComponent {
}
