package com.example.second;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class rate_Activity2 extends AppCompatActivity {
    EditText rate_usd,rate_gbp,rate_hkd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate2);
        Intent intent = getIntent();
        float usd = intent.getFloatExtra("usd",0.0f);
        float gbp = intent.getFloatExtra("gbp",0.0f);
        float hkd = intent.getFloatExtra("hkd",0.0f);
        rate_usd = findViewById(R.id.c_rate1);
        rate_gbp = findViewById(R.id.c_rate2);
        rate_hkd = findViewById(R.id.c_rate3);
        rate_usd.setText(String.valueOf(usd));
        rate_gbp.setText(String.valueOf(gbp));
        rate_hkd.setText(String.valueOf(hkd));
    }
    public void save(View v){
        rate_usd = findViewById(R.id.c_rate1);
        rate_gbp = findViewById(R.id.c_rate2);
        rate_hkd = findViewById(R.id.c_rate3);
        String inp_usd = rate_usd.getText().toString();
        String inp_gbp = rate_gbp.getText().toString();
        String inp_hkd = rate_hkd.getText().toString();
        float c_usd = Float.parseFloat(inp_usd);
        float c_gbp = Float.parseFloat(inp_gbp);
        float c_hkd = Float.parseFloat(inp_hkd);
    }
}