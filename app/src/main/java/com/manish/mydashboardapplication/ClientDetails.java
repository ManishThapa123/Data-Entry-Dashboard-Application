package com.manish.mydashboardapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.manish.mydashboardapplication.model.Client;
import com.manish.mydashboardapplication.model.ClientAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClientDetails extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    ChildEventListener mChildEventListener;
    private List<Client> mDataList;

    private RecyclerView recyclerView;
    private ClientAdapter clientAdapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_details);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");

        initViews();
        mDataList = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        clientAdapter = new ClientAdapter(this,mDataList);
        recyclerView.setAdapter(clientAdapter);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Client client = dataSnapshot.getValue(Client.class);
                mDataList.add(client);
                clientAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mRef.addChildEventListener(mChildEventListener);

    }

    private void initViews() {
    recyclerView = findViewById(R.id.recyclerView);
    }
}