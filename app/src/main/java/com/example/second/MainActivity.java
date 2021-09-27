package com.example.second;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi_counter);
        RadioGroup gender = findViewById(R.id.gender);
        RadioButton rb_man = findViewById(R.id.rb_man);
        RadioButton rb_woman = findViewById(R.id.rb_woman);
        EditText inp_height = findViewById(R.id.height);
        EditText inp_weight = findViewById(R.id.weight);
        TextView suggestion = findViewById(R.id.suggestion);
        Button submit = findViewById(R.id.sub);
        final String[] sex = new String[1];
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                double standards;
                if(i==rb_man.getId()){
                    sex[0] = "男";
                }else if(i==rb_woman.getId()){
                    sex[0] = "女";
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double standards=0.0;
                Float height = Float.valueOf(inp_height.getText().toString());
                Float weight = Float.valueOf(inp_weight.getText().toString());
                Float bmi = weight/(height*height);
                DecimalFormat decimalFormat=new DecimalFormat(".00");
                String BMI=decimalFormat.format(bmi);
                Log.i("111", "onCreate: "+bmi);
                if(sex[0]=="男"){
                    standards = (100*height-80)*0.7;
                    if(weight<0.9*standards){
                        suggestion.setText("您的BMI指数为："+BMI+"\n您的体重偏轻，请注意饮食！");
                    }else if(weight>1.1*standards){
                        suggestion.setText("您的BMI指数为："+BMI+"\n您的体重偏重，请注意饮食！");
                    }else {
                        suggestion.setText("BMI:"+BMI+"   男，体重正常");
                    }
                }else if(sex[0] == "女"){
                    standards = (100*height-70)*0.6;
                    if(weight<0.9*standards){
                        suggestion.setText("您的BMI指数为："+BMI+"\n您的体重偏轻，请注意饮食！");
                    }else if(weight>1.1*standards){
                        suggestion.setText("您的BMI指数为："+BMI+"\n您的体重偏重，请注意饮食！");
                    }else {
                        suggestion.setText("您的BMI指数为："+BMI+"\n您的体重正常，请继续保持！");
                    }
                }
                Log.i("111", "onClick: "+standards);
            }
        });
 //        EditText inp_ct = findViewById(R.id.inp_ct);
//        TextView show = findViewById(R.id.show);
//        Button sub = findViewById(R.id.sub);
//        sub.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Float ct = Float.valueOf(inp_ct.getText().toString());
//                double tp = (ct-32)/1.8;
//                show.setText(""+tp);
//                Log.i("111", ""+tp);
//            }
//        });
//       // this.onClick(sub);
    }
    public void myclick(View v){

    }

}