package com.semer.projet_android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<Event> list;
    Context context;
    public RecyclerAdapter(Context context, List<Event> list){
        this.context = context;this.list =list;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.test,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,@SuppressLint("RecyclerView")  int position) {
        Event event=list.get(position);
        holder.id.setText(String.valueOf(event.getId()));
        holder.VName.setText(event.getNom_event());
        holder.VDate.setText(event.getDate_event());
        holder.VLocale.setText(event.getLocal_event());

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra("id", String.valueOf(list.get(position).getId()));
                intent.putExtra("nom", String.valueOf(list.get(position).getNom_event()));
                intent.putExtra("locale", String.valueOf(list.get(position).getLocal_event()));
                intent.putExtra("date", String.valueOf(list.get(position).getDate_event()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,VName,VDate,VLocale;
        ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.event_id);
            VName = itemView.findViewById(R.id.event_name);
            VDate = itemView.findViewById(R.id.event_date);
            VLocale = itemView.findViewById(R.id.event_local);
            img = itemView.findViewById(R.id.imageView);
        }
    }
}
