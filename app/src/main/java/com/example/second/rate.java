package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class rate extends AppCompatActivity {
        TextView show;
        public  float USD = 0.1548f;
        public  float GBP = 0.1131f;
        public  float HKD = 1.2047f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);

    }
    public void onclick(View btn){
        EditText input = findViewById(R.id.inp_rate);
        show = findViewById(R.id.rate_show);
        String inp = input.getText().toString();
        float result = 0;
        Log.i("rate", ""+inp.length());
        if(inp.length()>0){
            float money = Float.parseFloat(inp);
            if(btn.getId()==R.id.d1){
                result = money*USD;
            }else if(btn.getId()==R.id.d2){
                result = money*GBP;
            }else if(btn.getId()==R.id.d3){
                result = money*HKD;
            }
            show.setText(String.valueOf(result));
        }else {
            show.setText("hello");
            Toast toast = Toast.makeText(this,"请输入金额后再进行计算",Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    public void send(View v){
        Intent intent = new Intent(this,rate_Activity2.class);
        intent.putExtra("usd",USD);
        intent.putExtra("gbp",GBP);
        intent.putExtra("hkd",HKD);
        startActivity(intent);
    }
}