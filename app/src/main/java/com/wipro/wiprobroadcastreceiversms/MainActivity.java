package com.wipro.wiprobroadcastreceiversms;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
                String messagePhoneNumber = String.format("smsto: %s", phoneNumberEditText.getText().toString());
                String message = messageEditText.getText().toString();

                Intent sendMessageIntent = new Intent(Intent.ACTION_SENDTO);
                sendMessageIntent.setData(Uri.parse(messagePhoneNumber));
                sendMessageIntent.putExtra("sms_body", message);

                if (sendMessageIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(sendMessageIntent);
                } else {
                    Toast.makeText(MainActivity.this, "Cannot Send SMS!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
