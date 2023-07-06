package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {

    String sys;
    String email;
    ArrayList<health>list;
    Context context;

    String d,t,b,s,dd;

    public dataAdapter(Context context,ArrayList<health>list,String email){
        this.context=context;
        this.list=list;
        this.email=email;
    }

    @NonNull
    @Override
    public dataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.data,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull dataAdapter.MyViewHolder holder, int position) {
            health adapter=list.get(position);

            holder.sys.setText(adapter.getSys());
            holder.dis.setText(adapter.getDis());
            holder.bp.setText(adapter.getBp());
            holder.date.setText(adapter.getDate());
            holder.time.setText(adapter.getTime());

<<<<<<< HEAD
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos= holder.getAdapterPosition();
                    health h = list.get(pos);
                    String DATE= h.getDate();

                    Intent intent = new Intent(context,DetailsActivity.class);
                    intent.putExtra("BP",h.bp);
                    intent.putExtra("SYS",h.sys);
                    intent.putExtra("DIAS",h.dis);
                    intent.putExtra("DATE_",h.date);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
=======
            b=adapter.getBp();
            s=adapter.getSys();
            dd=adapter.getDis();
            d=adapter.getDate();
            t=adapter.getTime();

            String dats=adapter.getDate().replace("/","");
            String tim=adapter.getTime().replace(":","");

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(email);
                    databaseReference.child(dats+tim).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(context, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(context,stat.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    });
                }
            });


>>>>>>> main
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView sys,dis,bp,time,date;
        Button update,delete;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            sys=itemView.findViewById(R.id.sys);
            dis=itemView.findViewById(R.id.dis);
            bp=itemView.findViewById(R.id.bp);
            date=itemView.findViewById(R.id.date);
            time=itemView.findViewById(R.id.time);
            update=itemView.findViewById(R.id.update);
            delete=itemView.findViewById(R.id.delete);

            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,Update.class);
                    intent.putExtra("bp",b);
                    intent.putExtra("dis",dd);
                    intent.putExtra("sys",s);
                    intent.putExtra("time",t);
                    intent.putExtra("date",d);
                    context.startActivity(intent);
                    ((Activity) context).finish();
                }
            });
        }
    }
}
