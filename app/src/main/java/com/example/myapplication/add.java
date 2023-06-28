package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class add extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText bp,sys,dis;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        bp=findViewById(R.id.bp);
        sys=findViewById(R.id.sys);
        dis=findViewById(R.id.dis);
        btn=findViewById(R.id.up);

        Intent intent1=getIntent();
        String email=intent1.getStringExtra("email");

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference(email);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bps=bp.getText().toString().trim();
                String syss=sys.getText().toString().trim();
                String di=dis.getText().toString().trim();

                health h=new health();
                h.setBp(bps);
                h.setDis(di);
                h.setSys(syss);

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        databaseReference.setValue(h);
                        Toast.makeText(add.this, "Data Uploaded", Toast.LENGTH_SHORT).show();
                        bp.setText("");
                        dis.setText("");
                        sys.setText("");
                        Intent intent=new Intent(add.this,MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}