package com.manish.mydashboardapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Dashboard extends AppCompatActivity {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private Button btn_newApplication;
    TextView txtSubmitted, txtPending, txtApproved;

    private int count;
    String Status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);




        txtSubmitted = findViewById(R.id.txt_applicationsSubmitted);
        txtPending = findViewById(R.id.txt_pendingApproval);
//        txtApproved = findViewById(R.id.txt_approved);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("users");
        readData();

        btn_newApplication = findViewById(R.id.btn_newDataEntry);



        btn_newApplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dashboard.this,NewApplication.class);
                startActivity(intent);
            }
        });


    }
    private void readData(){
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (dataSnapshot.exists()){
                    count = (int) dataSnapshot.getChildrenCount();
                    txtSubmitted.setText(Integer.toString(count));
                      txtPending.setText(Integer.toString(count));
                    Status = getIntent().getStringExtra("status");
                    if (Status!= null && Status.equals("Rejected")){
                        String a = String.valueOf(count-1);
                        txtSubmitted.setText(String.valueOf(a));
                        txtPending.setText(String.valueOf(a));
                    }else if(Status!= null && Status.equals("Approved")){
                        String b = String.valueOf(count-1);
                        txtPending.setText(b);
                    }


                }else{
                    txtSubmitted.setText("00");
                    txtPending.setText("00");
                }

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,HomePage.class);
        startActivity(intent);
    }
}