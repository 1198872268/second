package com.example.second;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;

public class rate extends AppCompatActivity implements Runnable{
        TextView show;
        public  float USD = 0.1548f;
        public  float GBP = 0.1131f;
        public  float HKD = 1.2047f;

        Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        USD = sp.getFloat("usd",0.0f);
        GBP = sp.getFloat("gbp",0.0f);
        HKD = sp.getFloat("hkd",0.0f);
        Log.i("sp", "onCreate: "+USD+"     "+GBP+"  "+HKD);
        if(USD==0){
            USD = 0.1548f;
        }
        if(GBP==0){
            GBP = 0.1131f;
        }
        if(HKD==0){
            HKD = 1.2047f;
        }

        //创建线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==1){
                    String str = (String)msg.obj;
                    Log.i("msg", "handleMessage: "+str);
                }
                super.handleMessage(msg);
            }
        };
    }

    public void onclick(View btn){
        EditText input = findViewById(R.id.inp_rate);
        show = findViewById(R.id.rate_show);
        String inp = input.getText().toString();
        float result = 0;

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
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==1&&resultCode==2){
            USD = data.getFloatExtra("usd",0.0f);
            GBP = data.getFloatExtra("gbp",0.0f);
            HKD = data.getFloatExtra("hkd",0.0f);
            Log.i("RESULT", "onCreate: "+USD+"     "+GBP+"  "+HKD);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void run() {
        Message msg = handler.obtainMessage(1);
        msg.obj = "hello run";
        Log.i("t", "run: .....");
        URL url = null;

        try {
            url = new URL("https://www.boc.cn/sourcedb/whpj/");
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            InputStream in = http.getInputStream();

            String html = inputStream2String(in);
            Log.i("html", "run: html="+html);
        } catch (Exception e) {
            e.printStackTrace();
        }

        handler.sendMessage(msg);
    }
    private String inputStream2String(InputStream inputStream) throws IOException {
        final int buffersize = 1024;
        final char[] buffer = new char[buffersize];
        final StringBuilder out = new StringBuilder();
        Reader in = new InputStreamReader(inputStream,"UTF-8");
        while(true){
            int rsz = in.read(buffer,0,buffer.length);
            if(rsz<0){
                break;
            }
            out.append(buffer,0,rsz);

        }
        return out.toString();
    }

}