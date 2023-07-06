package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    String REC,USER;
    private TextView delsys,deldias,deldate,delbp;
    private Button delererec,editrec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        delsys=findViewById(R.id.delsys);
        deldias=findViewById(R.id.deldias);
        delbp=findViewById(R.id.delbp);
        deldate=findViewById(R.id.deldate);
       //
        delererec=findViewById(R.id.deleterec);
        editrec=findViewById(R.id.editrec);

        delsys.setText(getIntent().getStringExtra("SYS")+" mm Hg");
        deldias.setText(getIntent().getStringExtra("DIAS")+" mm Hg");
        delbp.setText(getIntent().getStringExtra("BP" )+ " bpm");
        deldate.setText(getIntent().getStringExtra("DATE_"));

        editrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailsActivity.this,add.class);
                intent.putExtra("SYS",getIntent().getStringExtra("SYS"));
                intent.putExtra("DIAS",getIntent().getStringExtra("DIAS"));
                intent.putExtra("BP",getIntent().getStringExtra("BP"));
                intent.putExtra("DATE_",getIntent().getStringExtra("DATE_"));

                startActivity(intent);
            }
        });


        delererec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String date= getIntent().getStringExtra("DATE_");
               // FirebaseAuth mAuth = FirebaseAuth.getInstance();
                String mail="";
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null) {
                    mail = user.getEmail();
                    // Use the email address as needed
                }

                String email=mail.replace(".",",");
                String Datee = date.replace("/","");
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(email);
                databaseReference.removeValue();
                finish();

                //Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                //startActivity(intent);



            }
        });



    }




}