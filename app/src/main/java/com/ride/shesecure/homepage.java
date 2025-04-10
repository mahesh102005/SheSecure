package com.ride.shesecure;

import static android.widget.Toast.makeText;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.telephony.SmsManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {

    private ImageButton btnSos;
    private TextView textView, textView1, textView2, textView3;
    private String womenHelplineNumber = "9673363534"; // Women Safety Helpline Number
    private String friendNumber = "9673363534";

    // Replace with stored number or fetch from SharedPreferences

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // Initialize UI components
        btnSos = findViewById(R.id.btn_sos);
        textView = findViewById(R.id.textView);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);




        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + getString(R.string.police_number)));
                startActivity(callIntent);
            }
        });

        // Set click listener to call women helpline
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + getString(R.string.women_helpline_number)));
                startActivity(callIntent);
            }
        });

        textView3.setOnClickListener(v -> {
            String[] contacts = getResources().getStringArray(R.array.friend_contact);
            SmsManager smsManager = SmsManager.getDefault();

            for (String contact : contacts) {
                smsManager.sendTextMessage(contact, null, "I am in an emergency. Please help!", null, null);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this, AddNumberActivity.class);
                startActivity(intent);
                finish();

            }
        });
        // SOS Button Click - Call Women Helpline & Send Emergency SMS
        btnSos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWomenHelpline();
                sendEmergencySMS();
            }
        });
    }

    // Function to call the Women Safety Helpline
    private void callWomenHelpline() {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + womenHelplineNumber));
        startActivity(callIntent);
    }

    // Function to send an emergency SMS
    private void sendEmergencySMS() {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(friendNumber, null, "I am in emergency. Help me!", null, null);
         makeText(this, "Emergency SMS sent successfully!", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            makeText(this, "Failed to send SMS. Please check permissions.", Toast.LENGTH_SHORT).show();
        }
    }
}


