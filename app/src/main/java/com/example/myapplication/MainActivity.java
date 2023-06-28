package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    Button add,stat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.add);
        stat=findViewById(R.id.stat);

        Intent intent1=getIntent();
        String mail=intent1.getStringExtra("email");
        String email=mail.replace(".",",");



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, add.class);
                intent.putExtra("email",email);
                startActivity(intent);
            }
        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,stat.class);
                startActivity(intent);
            }
        });
    }
}