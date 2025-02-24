package com.prothesbarai.otpvarification2fa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Field;
import java.util.Random;
import java.util.Timer;

public class HomeActivity extends AppCompatActivity {
    private String[] countryCode;
    private AutoCompleteTextView countryCodes;
    private TextInputEditText phnNumberField;
    private AppCompatButton sendOTPBtn;
    private LinearLayout hiddenLayout;
    private TextView showOTPField,showOTPCounDownField;
    private volatile String otp = "";
    private long otpExpirationTime;
    private Timer countdownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.c5));
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.c5));
        setContentView(R.layout.home_activity);

        countryCodes = findViewById(R.id.countryCodes);
        phnNumberField = findViewById(R.id.phnNumberField);
        sendOTPBtn = findViewById(R.id.sendOTPBtn);
        showOTPField = findViewById(R.id.showOTPField);
        showOTPCounDownField = findViewById(R.id.showOTPCounDownField);
        hiddenLayout = findViewById(R.id.hiddenLayout);
        countryCode = getResources().getStringArray(R.array.country_code);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.country_code_items,R.id.countryCodeItems,countryCode);
        countryCodes.setAdapter(arrayAdapter);


        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countryCodes.getText().length()>0 && phnNumberField.getText().length()>0){
                    int otpLength = 6;
                    // Get Input Field CC & Phn no
                    String getCC = countryCodes.getText().toString();
                    String getPhnNo = phnNumberField.getText().toString().trim();
                    // ================ Generate OTP Start Here =====================
                    Random random = new Random();
                    StringBuilder otpBuilder = new StringBuilder();
                    for(int i=0; i<otpLength; i++){
                        otpBuilder.append(random.nextInt(10));
                    }
                    otp = otpBuilder.toString();
                    showOTPField.setText("OTP is : \t"+otp);
                    hiddenLayout.setVisibility(View.VISIBLE);
                    // ================ Generate OTP End Here =======================


                    //==================== OTP Stay Show Count Down Start Here =============================

                    //==================== OTP Stay Show Count Down End Here ===============================



                    countryCodes.setError(null);
                    phnNumberField.setError(null);
                } else if (countryCodes.getText().length()>0) {
                    countryCodes.setError(null);
                    if (phnNumberField.getText().length()>0){
                        phnNumberField.setError(null);
                    }else{
                        phnNumberField.setError("Empty");
                        hiddenLayout.setVisibility(View.GONE);
                    }
                } else if (phnNumberField.getText().length()>0) {
                    phnNumberField.setError(null);
                    if (countryCodes.getText().length()>0){
                        countryCodes.setError(null);
                    }else{
                        countryCodes.setError("Empty");
                        hiddenLayout.setVisibility(View.GONE);
                    }
                } else{
                    countryCodes.setError("Empty");
                    phnNumberField.setError("Empty");
                    hiddenLayout.setVisibility(View.GONE);
                }
            }
        });


    }
}