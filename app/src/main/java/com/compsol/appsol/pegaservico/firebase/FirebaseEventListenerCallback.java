package com.compsol.appsol.pegaservico.firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public interface FirebaseEventListenerCallback {

    void onChildAdded(DataSnapshot dataSnapshot);
    void onChildChanged(DataSnapshot dataSnapshot);
    void onChildRemoved(DataSnapshot dataSnapshot);
    void onChildMoved(DataSnapshot dataSnapshot);
    void onCancelled(DatabaseError error);

}
