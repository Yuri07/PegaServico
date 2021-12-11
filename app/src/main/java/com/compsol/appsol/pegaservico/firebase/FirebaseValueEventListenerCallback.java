package com.compsol.appsol.pegaservico.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface FirebaseValueEventListenerCallback {

    void onDataChange(DataSnapshot dataSnapshot);
    void onCanceled(DatabaseError error);

}
