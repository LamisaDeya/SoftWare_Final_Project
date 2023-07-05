package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.StartupTime;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    TextInputEditText email,pass;
    Button log;
    TextView reg,forgot;
    FirebaseAuth auth;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        log=findViewById(R.id.login);
        reg=findViewById(R.id.register);
        forgot=findViewById(R.id.forgot);

        auth=FirebaseAuth.getInstance();

       /* SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String token = preferences.getString("token", null);
        if(token.equals("true")){
            Intent intent1=new Intent(login.this,MainActivity.class);
            startActivity(intent1);
        }
        else {*/


            log.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String mail = email.getText().toString().trim();
                    String pas = pass.getText().toString().trim();
                    auth.signInWithEmailAndPassword(mail, pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("token", "true");
                                editor.apply();

                                Intent intent1 = new Intent(login.this, MainActivity.class);
                                startActivity(intent1);
                            }
                        }
                    });
                }
            });


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(login.this,Registration.class);
                startActivity(intent);
            }
        });

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}