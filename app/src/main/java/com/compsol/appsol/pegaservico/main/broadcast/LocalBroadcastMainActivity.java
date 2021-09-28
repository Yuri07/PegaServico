package com.compsol.appsol.pegaservico.main.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.compsol.appsol.pegaservico.main.ui.MainActivity;

public class LocalBroadcastMainActivity extends BroadcastReceiver {

    private MainActivity activity;

    public LocalBroadcastMainActivity(MainActivity activity ){
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String mensagem = intent.getStringExtra( MainActivity.MENSAGEM_KEY );
        //activity.requestTravel( mensagem );
    }

}
