package com.wipro.wiprobroadcastreceiversms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG_SMS_RECEIVED", "********************************************** SMS BROADCAST RECEIVED ****************************************");
        Toast.makeText(context, "BROADCAST RECEIVED SUCCESSFULLY!!!", Toast.LENGTH_LONG).show();

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED") && intent.getExtras() != null) {
            String broadcastMessage = intent.getExtras().getString("Broadcast_Receiver_Test");
            Toast.makeText(context, "MESSAGE RECEIVED FROM " + broadcastMessage, Toast.LENGTH_LONG).show();
        }
    }
}
