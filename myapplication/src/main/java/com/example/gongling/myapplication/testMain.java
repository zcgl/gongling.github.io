package com.example.gongling.myapplication;

import android.annotation.TargetApi;
import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gongling on 2016/5/9.
 */
public class testMain {
    @TargetApi(23)
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

        HashMap<String,Object> pmArrivalData=new  HashMap<String,Object>();
        pmArrivalData.put("code",3.0);
        //0 成功 ，1 出错， 2 失败 3已订阅
        if (pmArrivalData.get("code")!=null &&
                (Integer.valueOf("0").equals(pmArrivalData.get("code"))
        ||Double.valueOf("3").equals(pmArrivalData.get("code").toString()))) {
                       // ||"3.0".equals(pmArrivalData.get("code").toString()))) {

            System.out.println("设置成功");
        } else {
            System.out.println("设置失败");
        }

        List<String> codes=new ArrayList<String>();
        codes.add("qwe");
        codes.add("asd");
        codes.add("zxc");
        System.out.println(codes.toArray().toString());
        System.out.println(codes.toString());
        System.out.println(Arrays.asList(codes));
        System.out.println(Arrays.asList("qwe", "asd", "zxc"));

       String jsonStrs="{\"btns\":[{\"tabname\":\"我的抵用券\",\"url\":\"yhd://mycoupon\"}]}";

      /*  HashMap<String, String> mapParam = analysisParam(jsonStrs);
        System.out.println(mapParam.get("btns"));
        String listStr=mapParam.get("btns");
        HashMap<String, String> mapParam2 = analysisParam(listStr);*/

TextUtils.isEmpty("");


        List<String> ids=new ArrayList<>();
        ids.add(1+"");
        ids.add(2+"");
        ids.add(223+"");
        System.out.println(ids.toString());
        System.out.println(ids.toString().length());
        System.out.println(ids.toString().replace(" ", ""));
        System.out.println(ids.toString().substring(1, ids.toString().length() - 1));
        System.out.println(ids.toString().substring(1,ids.toString().length()-1).replace(" ", ""));
    }

    static HashMap<String, String> analysisParam(String paramsJson) {
        HashMap<String, String> mapParam = new HashMap<String, String>();
        if (!TextUtils.isEmpty(paramsJson)) {
            try {
                JSONObject tparam = new JSONObject(paramsJson);
                Iterator<?> objkey = tparam.keys();
                while (objkey.hasNext()) {
                    String jkey = objkey.next().toString();
                    String value = tparam.getString(jkey);
                    mapParam.put(jkey, value);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mapParam;
    }
}
