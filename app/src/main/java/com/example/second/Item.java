package com.example.second;

public class Item {
    public String cval;
    public String cname;

    public Item(String cval, String cname) {
        this.cval = cval;
        this.cname = cname;
    }

    public String getCval() {
        return cval;
    }

    public void setCval(String cval) {
        this.cval = cval;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
