package com.example.gongling.myapplication;

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

        String codeStr="sr";
        int code=Integer.valueOf(codeStr);
        System.out.println(Integer.valueOf("2").equals(code));
    }
}
