package io.genemoz.ppflib;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.genemoz.ppflib.databinding.ActivityLockerBinding;

public class LockerActivity extends AppCompatActivity {

    ActivityLockerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLockerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.pinLockView.attachIndicatorDots(binding.indicatorDots);
        binding.indicatorDots.setCount(binding.pinLockView.getPinLength());


    }
}