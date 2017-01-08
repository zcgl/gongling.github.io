package com.example.gongling.myapplication.javatest;

/**
 * Created by gongling on 2016/6/4.
 */
public class testclass implements Comparable<testclass>{
    private int first;
    private int integer;
    private String str1;
    private String str2;
    public int getInteger() {
        return integer;
    }
    public void setInteger(int integer) {
        this.integer = integer;
    }
    public String getStr1() {
        return str1;
    }
    public void setStr1(String str1) {
        this.str1 = str1;
    }
    public String getStr2() {
        return str2;
    }
    public void setStr2(String str2) {
        this.str2 = str2;
    }
    public testclass(int first ,Integer integer, String str1, String str2) {
        super();
        this.first=first;
        this.integer = integer;
        this.str1 = str1;
        this.str2 = str2;
    }
    public int compareTo(testclass o) {
        if(this.first<o.first) return -1;
        else if(this.first==o.first) {
            if (this.integer < o.integer) return -1;
            else if (this.integer == o.integer) return 0;
            else return 1;
        }else{
            return 1;
        }
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }
}