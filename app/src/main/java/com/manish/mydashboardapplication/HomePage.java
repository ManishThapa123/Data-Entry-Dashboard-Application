package com.manish.mydashboardapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomePage extends AppCompatActivity {
    private Button btnDataEntry;
    private Button btnValidate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        btnDataEntry = findViewById(R.id.btn_entry);
        btnValidate = findViewById(R.id.btn_validate);

    }

    public void dataEnter(View view) {
        Intent intent = new Intent(HomePage.this,Dashboard.class);
        startActivity(intent);


    }

    public void dataValidate(View view) {
        Intent intent = new Intent(HomePage.this,ClientDetails.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}