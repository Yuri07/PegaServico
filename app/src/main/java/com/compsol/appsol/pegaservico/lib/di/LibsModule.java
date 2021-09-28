package com.compsol.appsol.pegaservico.lib.di;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.compsol.appsol.pegaservico.lib.GlideImageLoader;
import com.compsol.appsol.pegaservico.lib.GreenRobotEventBus;
import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.lib.base.ImageLoader;
import com.compsol.appsol.pegaservico.lib.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LibsModule {

    private Fragment fragment;
    private Context context;

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setContext(Context context){
        this.context = context;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(){
        return new GreenRobotEventBus();
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader() {
        GlideImageLoader imageLoader = new GlideImageLoader();
        if (context != null) {
            imageLoader.setLoaderContext(context);
        }
        return imageLoader;
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(Context context, EventBus eventBus) {
        ImageStorage imageStorage = null;//new CloudinaryImageStorage(context, eventBus);
        return imageStorage;
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    Context providesContext(){
        return this.context;
    }

}
