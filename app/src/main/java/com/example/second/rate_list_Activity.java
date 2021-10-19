package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class rate_list_Activity extends AppCompatActivity {
    TextView re_title,re_rate;
    EditText inp_rmb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_list);

        re_title = findViewById(R.id.result_title);


        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String detail = intent.getStringExtra("detail");
        Log.i("xx", "list_onclick:title "+title);
        Log.i("xx", "list_onclick:detail "+detail);
        re_title.setText(title);

    }
    public  void list_onclick(View v){
        Intent intent = getIntent();
        String detail = intent.getStringExtra("detail");
        inp_rmb = findViewById(R.id.inp_rmb);
        re_rate = findViewById(R.id.result_rate);

        float rmb = Float.parseFloat(inp_rmb.getText().toString());
        float rate = Float.parseFloat(detail);

        float result = rmb*rate;


        re_rate.setText(String.valueOf(result));
    }
}