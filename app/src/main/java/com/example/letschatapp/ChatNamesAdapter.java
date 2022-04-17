package com.example.letschatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class ChatNamesAdapter extends RecyclerView.Adapter<ChatNamesAdapter.MyViewHolder> {
    Context context;
    List<UserData> userData;

    public ChatNamesAdapter(Context context, List<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.chats_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserData user = userData.get(position);
        holder.username.setText(user.getName());
        holder.lastmessage.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username,lastmessage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.item_username );
            lastmessage = itemView.findViewById(R.id.item_lastmessage);

        }
    }
}
