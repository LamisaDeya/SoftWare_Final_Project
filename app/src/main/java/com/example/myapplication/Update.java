package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update extends AppCompatActivity {

    EditText bp,sys,dis;
    TextView textView;
    String tm,mail;
    Button btn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update2);

        bp=findViewById(R.id.bp);
        sys=findViewById(R.id.sys);
        dis=findViewById(R.id.dis);
        btn=findViewById(R.id.up);
        textView=findViewById(R.id.tm);

        Intent intent=getIntent();
        String date=intent.getStringExtra("date");
        String time=intent.getStringExtra("time");
        String sis1=intent.getStringExtra("sys");
        String dis1=intent.getStringExtra("dis");
        String bp1=intent.getStringExtra("bp");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mail = user.getEmail();
            // Use the email address as needed
        }
       /* Intent intent1=getIntent();
        String mail=intent1.getStringExtra("email");*/

        String email=mail.replace(".",",");

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference(email);


        tm=date+" "+time;
        textView.setText(tm);
        bp.setText(bp1);
        dis.setText(dis1);
        sys.setText(sis1);

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
                h.setTime(time);
                h.setDate(date);

                String ss=date.replace("/","");
                String xx=time.replace(":","");



                reference.child(ss+xx).setValue(h);
                Toast.makeText(Update.this, "Data Uploaded", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(Update.this,stat.class);
                //intent.putExtra("ss",ss+xx);
                startActivity(intent);
                finish();
            }
        });
    }
}