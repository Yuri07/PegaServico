package com.compsol.appsol.pegaservico.firebaseservice;

import android.util.Log;

import com.compsol.appsol.pegaservico.firebaseservice.di.FIIDServiceComponent;

public class MyFirebaseInstanceIDService {

    private static final String TAG = "MyFirebaseIIDService";

    public MyFirebaseInstanceIDService() {
        //app = (TaxiLivreApp) getApplication();
        //setupInjection();

        Log.d("d", "MyFirebaseIIDService.Constructor() ");

    }

    private void setupInjection() {
        /*TaxiLivreApp app = (TaxiLivreApp) getApplication();
        if( app!=null ) {
            FIIDServiceComponent fiidServiceComponent = app.getFIIDServiceComponent();
            fiidServiceComponent.inject(this);
        }else{
            Log.d("d", "App esta recebendo nulo em  MyFirebaseInstanceIDService");
        }*/

    }

    public FIIDServiceComponent getFIIDServiceComponent(){
        return null;

    }

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    //@Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = null;//FirebaseInstanceId.getInstance().getToken();
        Log.d("d", "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {

        /*SharedPreferences sharedPreferences =

        sharedPreferences.edit().putString(TaxiLivreApp.FIREBASE_NOTIFICATION_TOKEN_KEY, token).apply();
        sharedPreferences.edit().putBoolean(TaxiLivreApp.FIREBASE_NOTIFICATION_TOKEN_UPDATED_KEY, false).apply();*/

        //firebaseAPI.sendTokenToServer(token);

        /*DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference myTokenReference = databaseReference.getRoot()
                .child(FirebaseAPI.NOTIFICATION_TOKEN_PATH)
                .child(getAuthUserEmail().replace(".","_"));
        myTokenReference.setValue(token);*/

    }

}
