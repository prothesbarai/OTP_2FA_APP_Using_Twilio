package com.prothesbarai.otpvarification2fa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputEditText;

public class HomeActivity extends AppCompatActivity {
    private String[] countryCode;
    private AutoCompleteTextView countryCodes;
    private TextInputEditText phnNumberField;
    private AppCompatButton sendOTPBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.c5));
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.c5));
        setContentView(R.layout.home_activity);

        countryCodes = findViewById(R.id.countryCodes);
        phnNumberField = findViewById(R.id.phnNumberField);
        sendOTPBtn = findViewById(R.id.sendOTPBtn);
        countryCode = getResources().getStringArray(R.array.country_code);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.country_code_items,R.id.countryCodeItems,countryCode);
        countryCodes.setAdapter(arrayAdapter);


        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });


    }
}