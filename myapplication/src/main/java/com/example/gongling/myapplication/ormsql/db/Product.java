package com.example.gongling.myapplication.ormsql.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by android_dev on 2016/11/9.
 */
@DatabaseTable(tableName = "tb_product")
public class Product {
    @DatabaseField(generatedId = true)
    private long id;
    @DatabaseField(columnName = "pid")
    private long pid;
    @DatabaseField(columnName = "name")
    private String name;
    @DatabaseField(columnName = "price")
    private double price;
    @DatabaseField(columnName = "imgurl")
    private String imgurl;
    @DatabaseField(columnName = "catid")
    private int catid;

    public Product(){

    }

    public Product(long pid,String name,double price,String imgurl){
        this.pid=pid;
        this.name=name;
        this.price=price;
        this.imgurl=imgurl;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public int getCatid() {
        return catid;
    }

    public void setCatid(int catid) {
        this.catid = catid;
    }
}
