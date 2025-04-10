package com.ride.shesecure;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNumberActivity extends AppCompatActivity {

    private EditText editTextFriendNumber;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_number);

        // Initialize UI components
        editTextFriendNumber = findViewById(R.id.editTextFriendNumber);
        btnDone = findViewById(R.id.btnDone);

        // Set click listener for the DONE button
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String friendNumber = editTextFriendNumber.getText().toString().trim();

                if (!friendNumber.isEmpty()) {
                    // Show confirmation message
                    Toast.makeText(AddNumberActivity.this, "Number saved successfully!", Toast.LENGTH_SHORT).show();

                    // Here, you can save the number in SharedPreferences or a database if needed
                } else {
                    // Show error if input is empty
                    Toast.makeText(AddNumberActivity.this, "Please enter a valid number!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
