package com.ride.shesecure;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class loginpage extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button loginButton;
    private TextView forgotPasswordText, signUpText;
    private ImageView backArrow;
    private UserDatabaseHelper dbHelper; // Database helper

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginpage);

        // Initialize views
        emailEditText = findViewById(R.id.email_username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login12);
        forgotPasswordText = findViewById(R.id.forgot_password);
        signUpText = findViewById(R.id.NewRegistration);
        backArrow = findViewById(R.id.Back_arrow);

        dbHelper = new UserDatabaseHelper(this); // Initialize database helper
//
//        // Back arrow click listener
//        backArrow.setOnClickListener(v -> {
//            Animation animation = AnimationUtils.loadAnimation(loginpage.this, R.anim.button_click);
//            v.startAnimation(animation);
//            Intent intent = new Intent(loginpage.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        });

        // Login button click listener
        loginButton.setOnClickListener(v -> {
            String emailOrName = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateUser(emailOrName, password)) {
                if (dbHelper.validateUser(emailOrName, password)) {
                    Toast.makeText(loginpage.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginpage.this, homepage.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(loginpage.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Forgot password click listener
        forgotPasswordText.setOnClickListener(v ->
                Toast.makeText(loginpage.this, "Forgot Password Clicked!", Toast.LENGTH_SHORT).show()
        );

        // Sign up click listener
        signUpText.setOnClickListener(v -> {
            Intent intent = new Intent(loginpage.this, userregistration.class);
            startActivity(intent);
        });
    }

    // Validate user inputs
    private boolean validateUser(String emailOrName, String password) {
        if (TextUtils.isEmpty(emailOrName)) {
            emailEditText.setError("Please enter your email or username");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Please enter your password");
            return false;
        }
        return true;
    }
}
