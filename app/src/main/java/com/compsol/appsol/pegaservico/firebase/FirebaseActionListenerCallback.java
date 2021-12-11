package com.compsol.appsol.pegaservico.firebase;

import com.google.firebase.database.DatabaseError;

public interface FirebaseActionListenerCallback {
    void onSuccess();
    void onError(DatabaseError error);
    void onError(Exception e);
    void onError();
}
