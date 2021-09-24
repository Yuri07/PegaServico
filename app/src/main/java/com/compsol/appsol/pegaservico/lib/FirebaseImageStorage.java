package com.compsol.appsol.pegaservico.lib;

import com.compsol.appsol.pegaservico.lib.base.EventBus;
import com.compsol.appsol.pegaservico.lib.base.ImageStorage;
import com.compsol.appsol.pegaservico.lib.base.ImageStorageFinishedListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;

public class FirebaseImageStorage implements ImageStorage {

    private EventBus eventBus;
    private FirebaseStorage firebaseStorage;

    @Override
    public String getImageUrl(String id) {
        return null;
    }

    @Override
    public void upload(File file, String id, ImageStorageFinishedListener listener) {

    }

}
