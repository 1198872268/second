package com.example.second;

import android.app.Activity;
import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends ListActivity implements Runnable{
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        List<String> list1 = new ArrayList<String>();
        for(int i = 1;i<100;i++){
            list1.add("item"+i);
        }
        //创建线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==2){
                    ArrayList<String>list2 = (ArrayList<String>) msg.obj;
                    Log.i("list", "handleMessage: "+list2);
                    ListAdapter adapter = new ArrayAdapter<String>(
                            MyListActivity.this,
                            android.R.layout.simple_expandable_list_item_1,
                            list2
                    );
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };

     }

    @Override
    public void run() {

//        URL url = null;
        SharedPreferences sp = getSharedPreferences("myrate", Activity.MODE_PRIVATE);
        List<String> retlist = new ArrayList<>();


            try {
                Document doc = Jsoup.connect("https://www.usd-cny.com/bankofchina.htm").get();
                Log.i("xx", "run: title :"+doc.title());
                Elements tables = doc.getElementsByTag("table");
                Element table1 = tables.first();
                Elements trs = table1.getElementsByTag("tr");
                trs.remove(0);
//获取table内的tr
                for(Element tr:trs){
                    Elements tds = tr.getElementsByTag("td");
                    String cname = tds.get(0).text();
                    String cval = tds.get(5).text();
                    retlist.add(cname+"--->"+cval);
                    Log.i("xx", "run: cname"+cname+"---->"+"cval:"+cval  );
                }
                Message msg = handler.obtainMessage(2);
                msg.obj = retlist;
                handler.sendMessage(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}