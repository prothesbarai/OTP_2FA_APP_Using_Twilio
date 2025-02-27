package com.prothesbarai.otpvarification2fa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private String[] countryCode;
    private AutoCompleteTextView countryCodes;
    private TextInputEditText phnNumberField,checkOTPField;
    private AppCompatButton sendOTPBtn,checkOTPBtn;
    private LinearLayout hiddenLayout,hiddenCheck,checkerLayout;
    private TextView showOTPField,showOTPCounDownField,outputOTPField;
    private volatile String otp = "";
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setStatusBarColor(getResources().getColor(R.color.c5));
        this.getWindow().setNavigationBarColor(getResources().getColor(R.color.c5));
        setContentView(R.layout.home_activity);

        countryCodes = findViewById(R.id.countryCodes);
        phnNumberField = findViewById(R.id.phnNumberField);
        checkOTPField = findViewById(R.id.checkOTPField);
        sendOTPBtn = findViewById(R.id.sendOTPBtn);
        checkOTPBtn = findViewById(R.id.checkOTPBtn);
        showOTPField = findViewById(R.id.showOTPField);
        showOTPCounDownField = findViewById(R.id.showOTPCounDownField);
        hiddenLayout = findViewById(R.id.hiddenLayout);
        hiddenCheck = findViewById(R.id.hiddenCheck);
        checkerLayout = findViewById(R.id.checkerLayout);
        outputOTPField = findViewById(R.id.outputOTPField);
        countryCode = getResources().getStringArray(R.array.country_code);

        ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,R.layout.country_code_items,R.id.countryCodeItems,countryCode);
        countryCodes.setAdapter(arrayAdapter);


        sendOTPBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(countryCodes.getText().length()>0 && phnNumberField.getText().length()>0){
                    int otpLength = 6;
                    // Get Input Field CC & Phn no
                    String getCC = countryCodes.getText().toString().trim();
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

                    // Send Otp Your Phn Using Tiwilo...
                    String phnNumbers = getCC+getPhnNo;

                    //==================== OTP Stay Show Count Down Start Here =============================
                    if (countDownTimer != null){
                        countDownTimer.cancel();
                    }
                    countDownTimer = new CountDownTimer(60000,1000) { // 60 সেকেন্ডের কাউন্টডাউন, প্রতি 1s আপডেট
                        @Override
                        public void onTick(long millis) {
                            long seconds = millis / 1000;
                            showOTPCounDownField.setText(seconds+"s");
                        }
                        @Override
                        public void onFinish() {
                            otp = "";
                            showOTPField.setText(null);
                            showOTPCounDownField.setText(null);
                            phnNumberField.setText(null);
                            hiddenLayout.setVisibility(View.GONE);
                            outputOTPField.setVisibility(View.GONE);
                        }
                    };
                    countDownTimer.start();
                    //==================== OTP Stay Show Count Down End Here ===============================
                    countryCodes.setError(null);
                    phnNumberField.setError(null);
                    Toast.makeText(HomeActivity.this, ""+phnNumbers, Toast.LENGTH_SHORT).show();
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



        // Check Button Start here ==============================================
        hiddenLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // When Visibility Based On Condition Enable and Disable
            @Override
            public boolean onPreDraw() {
                if (hiddenLayout.getVisibility() == View.VISIBLE){
                    checkerLayout.setVisibility(View.VISIBLE);
                    String getOTP = checkOTPField.getText().toString();
                    if (!getOTP.isEmpty()){
                        checkOTPBtn.setEnabled(true);
                        checkOTPBtn.setAlpha(1.0f);
                        checkOTPBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (checkOTPBtn.isEnabled()){
                                    hiddenCheck.setVisibility(View.VISIBLE);
                                    if(otp.equals(getOTP)){
                                        outputOTPField.setVisibility(View.VISIBLE);
                                        outputOTPField.setText("OTP Mached ! Success");
                                    }else{
                                        outputOTPField.setVisibility(View.VISIBLE);
                                        outputOTPField.setText("Invalid OTP");
                                    }
                                }else{
                                    hiddenCheck.setVisibility(View.GONE);
                                }
                            }
                        });
                    }else{
                        checkOTPBtn.setEnabled(false);
                        checkOTPBtn.setAlpha(0.1f);
                        hiddenCheck.setVisibility(View.GONE);
                    }
                }else{
                    checkerLayout.setVisibility(View.GONE);
                }
                return true;
            }
        });



    }

}