package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class otpRcv extends AppCompatActivity {

    EditText one,two,three,four,five,six;
    Button verify;
    TextView textView;
    ProgressBar progressBar;
    String code;
    String vercode;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_rcv);
        textView=findViewById(R.id.nmbr);
        verify=findViewById(R.id.verify);
        progressBar=findViewById(R.id.prog);

        Intent intent = getIntent();
        String id = intent.getStringExtra("number");
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String pass = intent.getStringExtra("pass");
        code=intent.getStringExtra("otp");
        textView.setText("+880"+id);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();

        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);

        setupOtpIn();

/**
 * This method verifies the user input verification code with the actual code
 */
        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                verify.setVisibility(View.INVISIBLE);
                String ver=one.getText().toString().trim()+
                        two.getText().toString().trim()+
                        three.getText().toString().trim()+
                        four.getText().toString().trim()+
                        five.getText().toString().trim()+
                        six.getText().toString().trim();
                //textView.setText(code);
                /*if (ver!=code){
                    progressBar.setVisibility(View.GONE);
                    verify.setVisibility(View.VISIBLE);
                    Toast.makeText(otpRcv.this, "Enter Correct OTP", Toast.LENGTH_SHORT).show();
                    return;
                }*/
                if (ver!=null){
                    PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(
                            code,
                            ver
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            verify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()){
                                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(otpRcv.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                                Intent newIntent=new Intent(otpRcv.this,login.class);
                                                startActivity(newIntent);
                                            }
                                    }
                                });
                            }else{
                                Toast.makeText(otpRcv.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
                }else{
                    Toast.makeText(otpRcv.this, "Enter OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void setupOtpIn(){
        one.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    two.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        two.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    three.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        three.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    four.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        four.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    five.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        five.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()){
                    six.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}