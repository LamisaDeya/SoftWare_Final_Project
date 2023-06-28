package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {

    TextInputEditText name,email,pass,conpass;
    Button regi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        pass=findViewById(R.id.pass);
        conpass=findViewById(R.id.conpass);
        regi=findViewById(R.id.regi);

        regi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nam=name.getText().toString().trim();
                String mail=email.getText().toString().trim();
                String pas=pass.getText().toString().trim();
                String conpas=conpass.getText().toString().trim();
                if (nam.isEmpty() || mail.isEmpty() || pas.isEmpty() || conpas.isEmpty()){
                    Toast.makeText(Registration.this, "Fill Up all Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pas.length()<6){
                    Toast.makeText(Registration.this, "Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pas.equals(conpas)){
                    Intent intent=new Intent(Registration.this,otpSend.class);
                    intent.putExtra("name",nam);
                    intent.putExtra("email",mail);
                    intent.putExtra("pass",conpas);
                    Toast.makeText(Registration.this, "Verify Your Mobile", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }
                else{
                    Toast.makeText(Registration.this, "Password didn't Match", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}