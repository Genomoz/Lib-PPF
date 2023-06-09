package io.genemoz.ppflib;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;

public class SplashScreen extends AppCompatActivity {

    MaterialCardView nxtBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        nxtBtn = findViewById(R.id.get_started_btn);
        nxtBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, LockerActivity.class));
            finish();
        });
    }
}