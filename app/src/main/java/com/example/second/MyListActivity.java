package com.example.second;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyListActivity extends ListActivity{
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        List<String> list1 = new ArrayList<String>();
        for(int i = 1;i<100;i++){
            list1.add("item"+i);
        }
        //创建线程
//        Thread t = new Thread(this);
//        t.start();


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
//            MyThread thread = new MyThread(handler);
     }


//    @Override
//    public void run() {
//        List<String> retlist = new ArrayList<>();
//        Document doc = null;
//            try {
//                for(int i = 0;i<10;i++){
//                    if (i==0){
//                        doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/"+"index.html").get();
//                    }else{
//                        doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/"+"index_"+i+".html").get();
//                    }
//                    Log.i("xx", "run: title :"+doc.title());
//                    Elements tables = doc.getElementsByTag("tbody");
//                    Element table1 = tables.get(1);
//                    Elements trs = table1.getElementsByTag("tr");
////                Elements tds = trs.first().getElementsByTag("td");
////                Log.i("run", "run: tds"+trs);
//                    trs.remove(0);
////获取table内的tr
//                    for(Element tr:trs){
//                        Elements tds = tr.getElementsByTag("td");
//                        String cname = tds.get(0).text();
//                        String cval = tds.get(5).text();
//                        retlist.add(cname+"--->"+cval);
//                        Log.i("xx", "run: cname"+cname+"---->"+"cval:"+cval  );
//                    }
//                }
//
//                Message msg = handler.obtainMessage(2);
//                msg.obj = retlist;
//                handler.sendMessage(msg);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//    }
}