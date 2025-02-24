package com.prothesbarai.otpvarification2fa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.reflect.Field;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private String[] countryCode;
    private AutoCompleteTextView countryCodes;
    private TextInputEditText phnNumberField;
    private AppCompatButton sendOTPBtn;
    private TextView showOTPField;

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
        countryCode = getResources().getStringArray(R.array.country_code);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.country_code_items,R.id.countryCodeItems,countryCode);
        countryCodes.setAdapter(arrayAdapter);


        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countryCodes.getText().length()>0 && phnNumberField.getText().length()>0){
                    int otpLength = 6;
                    String otp = "";
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
                    showOTPField.setText(otp);
                    showOTPField.setVisibility(View.VISIBLE);
                    // ================ Generate OTP End Here =======================





                    countryCodes.setError(null);
                    phnNumberField.setError(null);
                } else if (countryCodes.getText().length()>0) {
                    countryCodes.setError(null);
                    if (phnNumberField.getText().length()>0){
                        phnNumberField.setError(null);
                    }else{
                        phnNumberField.setError("Empty");
                        showOTPField.setVisibility(View.GONE);
                    }
                } else if (phnNumberField.getText().length()>0) {
                    phnNumberField.setError(null);
                    if (countryCodes.getText().length()>0){
                        countryCodes.setError(null);
                    }else{
                        countryCodes.setError("Empty");
                        showOTPField.setVisibility(View.GONE);
                    }
                } else{
                    countryCodes.setError("Empty");
                    phnNumberField.setError("Empty");
                    showOTPField.setVisibility(View.GONE);
                }
            }
        });


    }
}