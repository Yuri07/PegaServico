package com.compsol.appsol.pegaservico.firebase;

import com.compsol.appsol.pegaservico.entities.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class FirebaseAPI {

    private final static String USERS_PATH = "users";

    final static String NOTIFICATION_TOKEN_PATH = "notificationToken";
    final static String TOKEN_PATH = "token";

    private final static String SERVICES_PATH = "services";

    private final DatabaseReference databaseReference;

    private ChildEventListener userDataEventListener;
    private ChildEventListener historicServicesListEventListener;
    private ChildEventListener serviceEventListener;

    private final StorageReference storageReference;

    private final FirebaseAuth firebaseAuth;

    public FirebaseAPI(DatabaseReference databaseReference, StorageReference storageReference, FirebaseAuth firebaseAuth){//, AreasFortalezaHelper areasHelper){
        this.databaseReference = databaseReference;
        this.storageReference = storageReference;
        this.firebaseAuth = firebaseAuth;
    }

    public DatabaseReference getMyUserReference(){
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getUserReference(String email){
        DatabaseReference driverReference = null;
        if(email!=null){
            String emailKey = email.replace(".","_");
            driverReference = databaseReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return driverReference;
    }

    public String getAuthUserEmail(){
        FirebaseUser user = firebaseAuth.getCurrentUser();//FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if(user!=null)
            email = user.getEmail();

        return email;
    }

    public void changeUserConnectionStatus(long status) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("status", status);
            getMyUserReference().updateChildren(updates);

            //notifyContactsOfConnectionChange(status);
        }
    }

    /*public void notifyContactsOfConnectionChange(final long status) {
        final String myEmail = getAuthUserEmail();
        getMyHistoricChatsReference().addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String email = child.getKey();//um email de um contato meu
                    //Log.d("d", "firebase.HistoricChat.onDataChange "+email);
                    DatabaseReference reference = getOneContactReferenceofUser(email, myEmail);//pegando a
                    // referencia do meu contato para  avisa-lo que estou online ou offline(tornar true
                    // ou false o valor de 'users/email/contacts/myEmail').
                    reference.setValue(status);
                }

            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });
    }*/

    public void logout() {
        firebaseAuth.signOut();
        //FirebaseAuth.getInstance().signOut();
        //notifyContactsOfConnectionChange(User.OFFLINE, true);
        changeUserConnectionStatus(User.OFFLINE);
    }

    public void checkForSession(FirebaseActionListenerCallback listener) {
        FirebaseUser user = firebaseAuth.getCurrentUser();//FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            listener.onSuccess();
        } else {
            listener.onError(null);
        }
    }

    public void sendTokenToServer(String token, FirebaseActionListenerCallback listener) {

        String userEmail = getAuthUserEmail();
        if(userEmail!=null) {
            String userEmailKey = userEmail.replace(".","_");
            DatabaseReference myTokenReference = databaseReference.getRoot()
                    .child(NOTIFICATION_TOKEN_PATH)
                    .child(userEmailKey)
                    .child(TOKEN_PATH);
            myTokenReference.setValue(token);
            listener.onSuccess();
        }else{
            listener.onError(null);
        }

    }

    public void subscribeForDataUser(final FirebaseEventListenerCallback listener) {
        if(userDataEventListener==null) {
            userDataEventListener= new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listener.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    listener.onCancelled(databaseError);
                }
            };

            getMyUserReference().addChildEventListener(userDataEventListener);

        }
    }

    public void unSubscribeForDataUser() {
        getMyUserReference().removeEventListener(userDataEventListener);
    }

    public String createServiceId() {
        return getServicesReference().push().getKey();
    }

    public DatabaseReference getServicesReference() {
        String keySender = getAuthUserEmail().replace(".","_");
        return databaseReference.getRoot().child(USERS_PATH).child(keySender).child(SERVICES_PATH);
    }
}
