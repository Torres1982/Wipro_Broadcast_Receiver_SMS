package com.wipro.wiprobroadcastreceiversms;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        Log.i("TAG_SMS_RECEIVED", "********************************************** Can't resolve app for ACTION_SENDTO Intent ****************************************");
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            String number = intent.getExtras().getString("android.intent.extra.PHONE_NUMBER");
            Toast.makeText(context, "MESSAGE RECEIVED FROM " + number, Toast.LENGTH_LONG).show();
        }
    }
}
