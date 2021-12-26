package com.compsol.appsol.pegaservico.firebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.compsol.appsol.pegaservico.entities.ServiceItem;
import com.compsol.appsol.pegaservico.entities.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private final static String HISTORIC_SERVICES_PATH = "historicservices";

    private final static String SERVICES_PATH = "services";
    private final static String WAITING_SERVICES_PATH = "waitingservices";


    final static String NOTIFICATION_TOKEN_PATH = "notificationToken";
    final static String TOKEN_PATH = "token";



    private final DatabaseReference databaseReference;

    private ValueEventListener userDataEventListener;
    private ChildEventListener historicServicesListEventListener;
    private ChildEventListener servicesEventListener;

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
        DatabaseReference userReference = null;
        if(email!=null){
            String emailKey = email.replace(".","_");
            userReference = databaseReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
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
            listener.onError();
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
            listener.onError();
        }

    }

    public void subscribeForDataUser(final FirebaseValueEventListenerCallback listener) {
        if(userDataEventListener==null) {
            userDataEventListener= new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    listener.onDataChange(snapshot);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    listener.onCanceled(error);
                }
            };

            getMyUserReference().addValueEventListener(userDataEventListener);

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

    public void addService(ServiceItem service, FirebaseActionListenerCallback listenerCallback) {

        String newServiceId = createServiceId();
        service.setServiceId(newServiceId);

        Map<String, Object> serviceValues = service.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put("/"+USERS_PATH+"/"+ service.getEmail().replace(".","_")
                 + "/"+HISTORIC_SERVICES_PATH + "/" + service.getServiceId() , serviceValues);

        childUpdates.put("/"+SERVICES_PATH+"/" + WAITING_SERVICES_PATH + "/" +
                                                        service.getServiceId()  , serviceValues);

        databaseReference.updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error==null){
                    listenerCallback.onSuccess();
                }else{
                    listenerCallback.onError(error);
                }
            }
        });

        /*String newServiceId = createServiceId();
        service.setServiceId(newServiceId);
        DatabaseReference servicesReference = getServicesReference();
        servicesReference.child(service.getServiceId()).setValue(service)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        listenerCallback.onSuccess();
                    }
                }

                ).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        listenerCallback.onError(e);
                    }
                }
        );*/

        /*servicesReference.child(service.getServiceId()).setValue(service, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                listenerCallback.onSuccess();
            }

            @Override
            public void onError(DatabaseError error) {
                listenerCallback.onError(error);
            }
        });*/
    }

    public void subscribeForServicesOfferedUpates(final FirebaseChildEventListenerCallback listener) {
        if(servicesEventListener==null) {
            servicesEventListener= new ChildEventListener() {
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

            getOfferedServicessReference().addChildEventListener(servicesEventListener);

        }
    }

    public void unSubscribeForChatUpdates() {

        getOfferedServicessReference().removeEventListener(servicesEventListener);
        servicesEventListener = null;

    }

    public DatabaseReference getOfferedServicessReference(){
        String keyUser = getAuthUserEmail().replace(".","_");


        //String keyChat = keyReceiver + SEPARATOR + keySender;
        /*if (keySender.compareTo(keyReceiver) > 0) {//Esse método retorna um numero inteiro. Se ele for menor do que zero, o primeiro argumento é "menor" (alfabeticamente, nesse caso) que o segundo; maior que zero se o primeiro for "maior" que o segundo, e igual a zero se eles forem iguais. Esse método diferencia maiúsculas de minúsuclas. Se não quiser isso, use o compareToIgnoreCase
            keyChat = keyReceiver + SEPARATOR + keySender;//sempre o primeiro em ordem alfabetica vem primeiro
        }*/
        return databaseReference.getRoot().child(USERS_PATH).child(keyUser).child(HISTORIC_SERVICES_PATH);
    }


}
