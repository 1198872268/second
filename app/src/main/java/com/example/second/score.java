package com.example.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class score extends AppCompatActivity {
        int scorea = 0;
        int scoreb = 0;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
    }
    public void myclicka(View btn) {
        TextView show = findViewById(R.id.tva);
        if(btn.getId()==R.id.buta1){
            scorea += 3;
            show.setText(String.valueOf(scorea));
        }else if(btn.getId()==R.id.buta2){
            scorea += 2;
            show.setText(String.valueOf(scorea));
        }else if(btn.getId()==R.id.buta3){
            scorea += 1;
            show.setText(String.valueOf(scorea));
        }
    }

    public void myclickb(View btn) {
        TextView showb = findViewById(R.id.tvb);
        if(btn.getId()==R.id.butb1){
            scoreb += 3;
            showb.setText(String.valueOf(scoreb));
            
        }else if(btn.getId()==R.id.butb2){
            scoreb += 2;
            showb.setText(String.valueOf(scoreb));
        }else if(btn.getId()==R.id.butb3){
            scoreb += 1;
            showb.setText(String.valueOf(scoreb));
        }
    }

    public void reset(View btn){
        TextView showb = findViewById(R.id.tvb);
        TextView showa = findViewById(R.id.tva);
        showa.setText("0");
        showb.setText("0");

    }
}