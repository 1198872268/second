package com.example.second;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

public class MyListActivity2 extends AppCompatActivity implements Runnable, AdapterView.OnItemClickListener {
    Handler handler = new Handler();
    ListView listView;
    String TAG = "mylist2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list2);
        listView=findViewById(R.id.list1);
        listView.setOnItemClickListener(this);
//        List<String> list1 = new ArrayList<String>();
//        for(int i = 1;i<100;i++){
//            list1.add("item"+i);
//        }
        //创建线程
        Thread t = new Thread(this);
        t.start();

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==2){
                    ArrayList<HashMap<String,String>> list2 = (ArrayList<HashMap<String,String>>) msg.obj;
                    Log.i("list", "handleMessage: "+list2);
                    MyAdapter myadapter = new MyAdapter(MyListActivity2.this,R.layout.list_item,list2);
//                    ListAdapter adapter = new SimpleAdapter(MyListActivity2.this,
//                            list2,
//                            R.layout.list_item,
//                            new String[]{"ItemTitle","ItemDetail"},
//                            new int[]{R.id.itemTitle,R.id.itemDetail});

//                    Adapter<String>(
//                            MyListActivity2.this,
//                            android.R.layout.simple_expandable_list_item_1,
//                            list2
//                    );
                    listView.setAdapter(myadapter);
                }
                super.handleMessage(msg);
            }
        };

    }

    @Override
    public void run() {
//        List<String> retlist = new ArrayList<>();
        ArrayList<HashMap<String,String>> retlist = new ArrayList<HashMap<String,String>>();
        
        Document doc = null;
        try {
            for(int i = 0;i<10;i++){
                if (i==0){
                    doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/"+"index.html").get();
                }else{
                    doc = Jsoup.connect("https://www.boc.cn/sourcedb/whpj/"+"index_"+i+".html").get();
                }
                Log.i("xx", "run: title :"+doc.title());
                Elements tables = doc.getElementsByTag("tbody");
                Element table1 = tables.get(1);
                Elements trs = table1.getElementsByTag("tr");
//                Elements tds = trs.first().getElementsByTag("td");
//                Log.i("run", "run: tds"+trs);
                trs.remove(0);
//获取table内的tr
                for(Element tr:trs){
                    Elements tds = tr.getElementsByTag("td");
                    String cname = tds.get(0).text();
                    String cval = tds.get(5).text();
                    HashMap<String,String> map = new HashMap<String,String>();
                    map.put("ItemTitle",cname);
                    map.put("ItemDetail",cval);
                    retlist.add(map);
//                    retlist.add(cname+"--->"+cval);
                    Log.i("xx", "run: cname"+cname+"---->"+"cval:"+cval  );
                }
            }

            Message msg = handler.obtainMessage(2);
            msg.obj = retlist;
            handler.sendMessage(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Object itemAtPosition = listView.getItemAtPosition(position);
        HashMap<String,String> map = (HashMap<String,String>)itemAtPosition;
        String titlestr = map.get("ItemTitle");
        String detailstr = map.get("ItemDetail");
        Log.i(TAG, "onItemClick:title "+titlestr);
        Log.i(TAG, "onItemClick: detail"+detailstr);
//发送消息
        Intent intent = new Intent(this,rate_list_Activity.class);
        intent.putExtra("title",titlestr);
        intent.putExtra("detail",detailstr);
        startActivityForResult(intent,2);

    }
}