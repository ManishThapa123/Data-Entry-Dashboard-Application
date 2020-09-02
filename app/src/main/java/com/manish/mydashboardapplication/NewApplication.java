package com.manish.mydashboardapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.manish.mydashboardapplication.model.Client;

public class NewApplication extends AppCompatActivity {
    private static final String TAG = "NewApplication";


    private EditText mClientNumber, mPropertyName, mCityName, mAreaName, mOwnersName, mLanguage;
    private Button buttonSubmit;
    private Button validate, goBack;
    private CardView cardView;



    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private DatabaseReference mRefVal;

    private ChildEventListener mChildEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_application);
        initViews();
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("users");


        mRefVal = mDatabase.getReference("users");


        //Child Listener
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Client client = dataSnapshot.getValue(Client.class);
                Log.d(TAG, "onChildAdded: " + client.getClientNumber());
                Log.d(TAG, "onChildAdded: " + client.getPropertyName());
                Log.d(TAG, "onChildAdded: " + client.getCityName());
                Log.d(TAG, "onChildAdded: " + client.getArea());
                Log.d(TAG, "onChildAdded: " + client.getOwnersName());
                Log.d(TAG, "onChildAdded: " + client.getLanguage());
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
        //to attach the listener to the reference.
    }

    private void validateNumber() {
        final long clientNumberValidate = Long.parseLong(mClientNumber.getText().toString());
        mRefVal.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(String.valueOf(clientNumberValidate))){
                    Toast.makeText(NewApplication.this, "Number already Exists", Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(NewApplication.this, "Number Validated", Toast.LENGTH_SHORT).show();
                    cardView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mRef.removeEventListener(mChildEventListener);
//    }

    private void initViews() {
        cardView = findViewById(R.id.cardViewForm);
        mClientNumber = findViewById(R.id.client_number);
        mPropertyName = findViewById(R.id.propertyName);
        mCityName = findViewById(R.id.city);
        mAreaName = findViewById(R.id.area);
        mOwnersName = findViewById(R.id.owner);
        mLanguage = findViewById(R.id.language);
        buttonSubmit = findViewById(R.id.btn_submit);
        validate = findViewById(R.id.btn_validate);
        goBack = findViewById(R.id.goBack);

    }

    public void onSubmit(View view) {
        
        if (checkValidation()) {
            long clientNumber =Long.parseLong(mClientNumber.getText().toString());
            String propertyName = mPropertyName.getText().toString();
            String cityName = mCityName.getText().toString();
            String area = mAreaName.getText().toString();
            String ownersName = mOwnersName.getText().toString();
            String language = mLanguage.getText().toString();


            Client client = new Client(clientNumber, propertyName, cityName, area, ownersName, language);

            mRef.child(String.valueOf(clientNumber)).setValue(client);


//        mRef.setValue(client);
            Toast.makeText(this, "Data inserted...", Toast.LENGTH_LONG).show();

            mPropertyName.setText("");
            mCityName.setText("");
            mAreaName.setText("");
            mOwnersName.setText("");
            mLanguage.setText("");
//            cardView.setVisibility(View.INVISIBLE);

        }

    }

    private boolean checkValidation() {

        String propertyName = mPropertyName.getText().toString();
        String cityName = mCityName.getText().toString();
        String area = mAreaName.getText().toString();
        String ownersName = mOwnersName.getText().toString();
        String language = mLanguage.getText().toString();

       if (propertyName.length() <= 0) {
            mPropertyName.requestFocus();
            mPropertyName.setError("Enter Property Name");
            return false;

        } else if (cityName.length() <= 0) {
            mCityName.requestFocus();
            mCityName.setError("Enter City ");
            return false;

        } else if (area.length() <= 0) {
            mAreaName.requestFocus();
            mAreaName.setError("Enter Area");
            return false;

        } else if (ownersName.length() <= 0) {

            mOwnersName.setText("NA");
            return false;

        } else if (language.length() <= 0) {

            mLanguage.setText("NA");
            return false;


        } else {
            return true;
        }
    }

    public void onValidate(View view) {
        validateNumber();




    }

    public void goBack(View view) {
                Intent intent = new Intent(NewApplication.this, Dashboard.class);
      startActivity(intent);
      cardView.setVisibility(View.INVISIBLE);

    }
}