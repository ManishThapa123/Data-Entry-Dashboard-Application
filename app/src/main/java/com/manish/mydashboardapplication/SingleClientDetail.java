package com.manish.mydashboardapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SingleClientDetail extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7;
    private Button button;
    public String Approved;
    public String Declined;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_client_detail);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource
                (this,R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        String propertyName = getIntent().getStringExtra("pname");
        String city = getIntent().getStringExtra("city");
        String area = getIntent().getStringExtra("area");
        final String cNumber = getIntent().getStringExtra("cNumber");
        String owner = getIntent().getStringExtra("oName");
        String language = getIntent().getStringExtra("pLang");
        String status = getIntent().getStringExtra("pending");

        initViews();

        txt1.setText("propertyName: " + propertyName);
        txt2.setText("city: " + city);
        txt3.setText("area: " + area);
        txt4.setText("cNumber: " + cNumber);
        txt5.setText("owner: " + owner);
        txt6.setText("language: " + language);
        txt7.setText("status: " + status);

        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + cNumber));
                startActivity(callIntent);
            }
        });


    }

    private void initViews() {
        txt1 = findViewById(R.id.txtprop);
        txt2 = findViewById(R.id.txtLoc);
        txt3 = findViewById(R.id.txtAr);
        txt4 = findViewById(R.id.txtNum);
        txt5 = findViewById(R.id.txtNam);
        txt6 = findViewById(R.id.txtLang);
        txt7 = findViewById(R.id.txtStat);
        button = findViewById(R.id.callClient);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String status = parent.getItemAtPosition(position).toString();
        txt7.setText(status);

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Submit(View view) {
        String applicationStatus = txt7.getText().toString();
        Intent intent = new Intent(SingleClientDetail.this, Dashboard.class);
        intent.putExtra("status",applicationStatus);

        startActivity(intent);
    }
}