package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpSend extends AppCompatActivity {

    PhoneAuthProvider phoneAuthProvider;
    TextView phone;
    Button send;
    ProgressBar progressBar;
    FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_send);
        phone=findViewById(R.id.phone);
        send=findViewById(R.id.send);
        progressBar=findViewById(R.id.prog);

        Intent intent1=getIntent();
        String name=intent1.getStringExtra("name").toString().trim();
        String email=intent1.getStringExtra("email").toString().trim();
        String pass=intent1.getStringExtra("pass").toString().trim();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number;
                number=phone.getText().toString().trim();
                if (number.isEmpty()){
                    Toast.makeText(otpSend.this, "Enter your phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (number.length()<10){
                    Toast.makeText(otpSend.this, "Enter correct phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    send.setVisibility(View.INVISIBLE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+880"+number,
                            60,
                            TimeUnit.SECONDS,
                            otpSend.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBar.setVisibility(View.GONE);
                                    send.setVisibility(View.VISIBLE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBar.setVisibility(View.GONE);
                                    send.setVisibility(View.VISIBLE);
                                    Toast.makeText(otpSend.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                   // super.onCodeSent(s, forceResendingToken);
                                    progressBar.setVisibility(View.GONE);
                                    send.setVisibility(View.VISIBLE);
                                    Toast.makeText(otpSend.this, "OTP sent", Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(otpSend.this,otpRcv.class);
                                    intent.putExtra("number",number);
                                    intent.putExtra("otp",s);
                                    intent.putExtra("name",name);
                                    intent.putExtra("email",email);
                                    intent.putExtra("pass",pass);
                                    startActivity(intent);

                                }
                            }
                    );
                }

            }
        });
    }
}