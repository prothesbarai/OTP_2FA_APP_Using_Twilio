package com.prothesbarai.otpvarification2fa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.c5));
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.c5));
        setContentView(R.layout.home_activity);
    }
}