package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class add extends AppCompatActivity {


    EditText bp, sys, dis;
    TextView textView;
    String tm, mail;
    Button btn;
    long cnt = 0;

    /**
     * This add data into firebase
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        bp = findViewById(R.id.bp);
        sys = findViewById(R.id.sys);
        dis = findViewById(R.id.dis);
        btn = findViewById(R.id.up);
        textView = findViewById(R.id.tm);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            mail = user.getEmail();


            String email = mail.replace(".", ",");

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(email);


            Date date = Calendar.getInstance().getTime();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm:ss", Locale.getDefault());

            String dat = simpleDateFormat.format(date);
            String tim = simpleTimeFormat.format(date);

            tm = tim + " " + dat;

            textView.setText(tm);

            /**
             * This inserts data into firebase realtime database
             */
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    String bps = bp.getText().toString().trim();
                    String syss = sys.getText().toString().trim();
                    String di = dis.getText().toString().trim();

                    health h = new health();
                    h.setBp(bps);
                    h.setDis(di);
                    h.setSys(syss);
                    h.setTime(tim);
                    h.setDate(dat);

                    String ss = dat.replace("/", "");
                    String xx = tim.replace(":", "");


                    reference.child(ss + xx).setValue(h);
                    Toast.makeText(add.this, "Data Uploaded", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(add.this, MainActivity.class);
                    intent.putExtra("ss", ss + xx);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}