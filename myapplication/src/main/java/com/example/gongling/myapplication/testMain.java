package com.example.gongling.myapplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gongling on 2016/5/9.
 */
public class testMain {

    public static void main(String srgs[]){
        String stockDescInfo="运费xx,由配送并提供售后服务";
        int insertStart=stockDescInfo.indexOf("由配送并提供");
        StringBuffer stockDesc=new StringBuffer();
        stockDesc.append(stockDescInfo.substring(0,insertStart+1));
        stockDesc.append("wcgvho");
        stockDesc.append(stockDescInfo.substring(insertStart + 1, stockDescInfo.length()));

        System.out.print(stockDesc.toString());

        String priceText="55.";
        System.out.println(Double.valueOf(priceText));

       /* String codeStr="sr";
        int code=Integer.valueOf(codeStr);
        System.out.println(Integer.valueOf("2").equals(code));*/

        int a=100;
        int b=68;
        System.out.println(a+b/100.0);

        String content="sss淘宝的商品没有京东好，没有京东,国美的好，也没有1号店的好";
        String words[]="sss淘宝,京东,国美".split(",");
        for(String word:words){
            int length=word.length();
            StringBuffer newWord=new StringBuffer();
            for(int i=0;i<length-1;i++) {
                newWord.append("某");
            }
            newWord.append(word.charAt(length-1));
            content=content.replace(word,newWord.toString());
        }


        System.out.println(content);

        List<String> aastr=new ArrayList<String>();
                aastr.add("2");
        aastr.add("3");
        aastr.add("4");
        aastr.add("5");
        System.out.println(aastr);
        System.out.println(aastr.subList(0,3));

        List<testclass> list = new ArrayList<testclass>();
        list.add(new testclass(1,1,"",""));
        list.add(new testclass(1,3,"",""));
        list.add(new testclass(1,2,"",""));
        list.add(new testclass(-1,6,"",""));
        list.add(new testclass(-1,9, "", ""));
        list.add(new testclass(2,7, "", ""));
        list.add(new testclass(3,1,"",""));
        list.add(new testclass(1,3,"",""));
        list.add(new testclass(-1,2,"",""));
        list.add(new testclass(3,6,"",""));
        list.add(new testclass(3,9,"",""));
        list.add(new testclass(3,7,"",""));
        System.out.print("排序前:");
        for (testclass t : list) {
            System.out.print(t.getFirst()+" "+t.getInteger()+"\n ");
        }
        Collections.sort(list);//自动调用compareTo
        System.out.print("\n排序后:");
        for (testclass t : list) {
            System.out.print(t.getFirst()+" "+t.getInteger()+"\n ");
        }


        String dValue = "36.99";
        String[] splitArray = dValue.split("\\.");
        System.out.print(splitArray.length+" "+splitArray);
        if (splitArray.length > 1) {
            String dotValue = splitArray[1];
            int diff = dotValue.length();
            if (diff > 0) {
                System.out.print(dotValue);
            }
        }
    }
}
