package com.example.second;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class MyThread implements Runnable{
    Handler handler;
    @Override
    public void run() {
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
