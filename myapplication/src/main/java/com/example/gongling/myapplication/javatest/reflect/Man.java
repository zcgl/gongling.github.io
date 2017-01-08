package com.example.gongling.myapplication.javatest.reflect;

import java.util.Observable;
import java.util.Observer;

public class Man extends Person implements Observer {

    private int age = 0;
    private String var1 = "I am var1";
    public int var2 = 20;

    public Man() {
        System.out.println("I am Man" + var1);
        age = 20;
    }

    public int myAge() {
        return 28;
    }

    public String myName() {
        return "Jerome";
    }

    private void getName(){
        System.out.println("I am Jerome");
    }

    /**
     *@hide
     */
    private void getAge(){
        System.out.println("I am "+age);
    }

    @Override
    void getPhone() {
        System.out.println("I am sun , My age is " + age + "___" + var2);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}