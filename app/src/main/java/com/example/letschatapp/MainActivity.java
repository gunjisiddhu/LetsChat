package com.example.letschatapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ImageView logout;
    TextView textView;
    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;
    List<UserData> userDataList;
    ChatNamesAdapter chatNamesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            startActivity(new Intent(getApplicationContext(),LoginScreen.class));
            finish();
        }
        setValues();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.e("Rec",snapshot.toString());
                if(!snapshot.equals(null)){
                    for(DataSnapshot snap:snapshot.getChildren()){
                        Log.e("Rec",snap.toString());
                        userDataList.add(snap.getValue(UserData.class));
                    }
                }
                chatNamesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        Log.e("data",userDataList.toString());

        /*
        DateTime dt = new DateTime();
        String dtStr = dt.toString();

        Log.e("First", dt+"   "+dtStr);
        DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
        DateTime newDt = fmt.parseDateTime(dtStr);
        Log.e("second",newDt+"  "+dtStr);
        */



    }

    private void setValues() {
        //firebaseAuth.getInstance().signOut();
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            startActivity(new Intent(getApplicationContext(),LoginScreen.class));
            finish();
        }
        DatabaseReference data = FirebaseDatabase.getInstance().getReference("Users").child(firebaseAuth.getUid());
        data.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                UserData userData = snapshot.getValue(UserData.class);
                textView.setText(userData.getName());}
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView = findViewById(R.id.mainmenu_recylerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        userDataList = new ArrayList<>();
        chatNamesAdapter = new ChatNamesAdapter(getApplicationContext(),userDataList);
        recyclerView.setAdapter(chatNamesAdapter);





        textView = findViewById(R.id.mainmenu_username);
        logout = findViewById(R.id.mainmenu_logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });

    }
}