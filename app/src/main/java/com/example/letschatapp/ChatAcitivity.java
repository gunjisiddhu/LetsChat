package com.example.letschatapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ChatAcitivity extends AppCompatActivity {
    TextView receivername,receiverlastseen;
    RecyclerView recyclerView;
    EditText sendText;
    ImageView sendButton;
    List<Message> messages;
    ChatMessagesAdapter chatMessagesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_acitivity);


        setVals();
        messages.add(new Message("siddhu","s"));
        messages.add(new Message("siddhu","s"));
        messages.add(new Message("siddhu","s"));
        messages.add(new Message("siddhu","s"));
        messages.add(new Message("siddhu","s"));
        chatMessagesAdapter.notifyDataSetChanged();
    }

    private void setVals() {
        receivername = findViewById(R.id.chat_receivername);
        receiverlastseen = findViewById(R.id.chat_receiverlastseen);
        recyclerView = findViewById(R.id.chat_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        sendText = findViewById(R.id.chat_sendMessage);
        sendButton = findViewById(R.id.chat_sendButton);
        messages = new ArrayList<>();
        chatMessagesAdapter = new ChatMessagesAdapter(getApplicationContext(),messages);
        recyclerView.setAdapter(chatMessagesAdapter);
    }
}