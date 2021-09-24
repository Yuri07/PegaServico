package com.compsol.appsol.pegaservico.firebase.di;

import android.app.Application;

import com.compsol.appsol.pegaservico.firebase.FirebaseAPI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.inject.Singleton;

import dagger.Provides;

public class FirebaseModule {

    public FirebaseModule() {
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(DatabaseReference databaseReference, StorageReference storageReference, FirebaseAuth firebaseAuth) {
        return new FirebaseAPI(databaseReference, storageReference, firebaseAuth);
    }

    @Provides
    @Singleton
    DatabaseReference providesFirebase() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Provides
    @Singleton
    StorageReference providesStorageReference(){
        return FirebaseStorage.getInstance().getReference();
    }

    @Provides
    @Singleton
    FirebaseAuth providesFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }

}
