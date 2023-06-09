package io.genemoz.ppflib;

import static io.genemoz.ppf.PatternLockView.stringToPattern;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.genemoz.ppf.PPFSecurity;
import io.genemoz.ppf.PatternLockView;
import io.genemoz.ppflib.databinding.ActivityPatternBinding;

public class PatternActivity extends AppCompatActivity {

    ActivityPatternBinding binding;

    PPFSecurity ppfSecurity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatternBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.switchLock.setOnClickListener(v -> {
            startActivity(new Intent(this, LockerActivity.class));
            finish();
        });


        ppfSecurity = new PPFSecurity(this);

        if (!ppfSecurity.isPatternSet()) {
            binding.patternLockView.setPattern(PatternLockView.PatternViewMode.AUTO_DRAW, stringToPattern(binding.patternLockView, "01234"));
        } else {
            Toast.makeText(this, "Enter your pattern", Toast.LENGTH_SHORT).show();
        }


        binding.patternLockView.addPatternLockListener(new PatternLockView.PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {

                if (ppfSecurity.isPatternSet()) {
                    if (ppfSecurity.isPatternCorrect(PatternLockView.patternToString(binding.patternLockView, pattern))) {
                        startActivity(new Intent(PatternActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(PatternActivity.this, "Wrong Pattern", Toast.LENGTH_SHORT).show();
                        binding.patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);

                        new Handler().postDelayed(() -> {
                            binding.patternLockView.clearPattern();
                        }, 1000);

                    }
                } else {
                    ppfSecurity.setPattern(PatternLockView.patternToString(binding.patternLockView, pattern));
                    Toast.makeText(PatternActivity.this, "Pattern Set", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onCleared() {

            }
        });


    }
}