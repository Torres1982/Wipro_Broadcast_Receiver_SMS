package com.wipro.wiprobroadcastreceiversms;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText phoneNumberEditText;
    EditText messageEditText;
    Button sendMessageButton;

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

    protected void sendTextMessages() {
        String phoneNumber = String.format("smsto: %s", phoneNumberEditText.getText().toString());
        String message = messageEditText.getText().toString();

        Log.i("TESTING_SMS_1", "************************************************ TREYING TO SEND SMS!!! *********************************************************");

        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse(phoneNumber));
        smsIntent.putExtra("sms_body", message);

        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Log.i("TAG_SMS_SENT_ERROR", "Can't resolve app for ACTION_SENDTO Intent");
        }

        Log.i("TESTING_SMS_2", "************************************************ TREYING TO SEND SMS!!! *********************************************************");
    }
}
