package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class dataAdapter extends RecyclerView.Adapter<dataAdapter.MyViewHolder> {

    String sys;
    String dis;
    ArrayList<health>list;
    Context context;

    public dataAdapter(Context context,ArrayList<health>list){
        this.context=context;
        this.list=list;
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

        }
    }
}
