package com.wipro.wiprobroadcastreceiversms;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText phoneNumberEditText;
    EditText messageEditText;
    Button sendMessageButton;
    BroadcastReceiver smsBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phoneNumberEditText = findViewById(R.id.edit_text_phone_number);
        messageEditText = findViewById(R.id.edit_text_sms);
        sendMessageButton = findViewById(R.id.button_send_sms);

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessages();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(smsBroadcastReceiver);
    }

    private void sendTextMessages() {
        String phoneNumber = String.format("smsto: %s", phoneNumberEditText.getText().toString());
        String message = messageEditText.getText().toString();

        Log.i("TESTING_SMS_1", "************************************************ TRYING TO SEND SMS!!! *********************************************************");

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse(phoneNumber));
        smsIntent.putExtra("sms_body", message);

        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
            registerBroadcast();
        } else {
            Log.i("TAG_SMS_SENT_ERROR", "Can't resolve app for ACTION_SEND_TO Intent");
        }
    }

    // Register Broadcast Receiver
    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        smsBroadcastReceiver = new MySMSReceiver();
        registerReceiver(smsBroadcastReceiver, intentFilter);

        sendBroadcast();
    }

    // Send Broadcast Receiver
    private void sendBroadcast() {
        Intent broadcastIntent = new Intent();
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        broadcastIntent.setAction("android.provider.Telephony.SMS_RECEIVED");
        broadcastIntent.putExtra("Broadcast_Receiver_Test", "This is a Test Message sent by Broadcast!");
        sendBroadcast(broadcastIntent);
    }
}

//            Intent broadcastIntent = new Intent("sms_sent_broadcast");
//            // You can also include some extra data.
//            broadcastIntent.putExtra("message", "This is my message for sent broadcast!");
//            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
