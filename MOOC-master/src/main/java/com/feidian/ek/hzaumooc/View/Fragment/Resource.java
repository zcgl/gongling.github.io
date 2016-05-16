package com.feidian.ek.hzaumooc.View.Fragment;

import android.support.annotation.NonNull;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2016/4/21.
 */
public class Resource {
    private String url="";
    List<Map<String,String>> list;
    static final String main_url="http://course.hzau.edu.cn";
    static final String l="http://course.hzau.edu.cn/G2S/";//资源有关的网基址
    public Resource(String url,List<Map<String,String>> list)
    {
        this.url=url;
        this.list=list;
    }
    public boolean findResource(String value)
    {
        try {
            Document document= Jsoup.parse(new URL(url),5000);
            Elements elements=document.getElementsContainingOwnText(value);
            System.out.println(url);
            if(elements.hasText())
            {
                String url=elements.first().getElementsByTag("a").attr("href").trim();
                url=main_url+url;
                document=Jsoup.parse(new URL(url),5000);
                elements=document.getElementsByAttributeValue("target", "_blank");
                initValue(elements);
                return true;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }
    private void initValue(Elements elements) {
        // TODO Auto-generated meb
        for(Element d:elements)
        {
            Elements wenjian = d.getElementsByTag("a");
            Map<String,String> map=new HashMap<String, String>();
            map.put("name",d.text());
            String web=addResourceweb(wenjian.attr("href").trim());
            map.put("web",web);
            list.add(map);
        }

    }
    private String addResourceweb(String d)
    {
        String resourceweb="";
        StringBuilder string=new StringBuilder();
        string.append(d);
        string.delete(0, 3);
        url=l+string.toString();
        System.out.println(url);
        try {
            Document document=Jsoup.connect(url).get();
            Elements element=document.getElementsByTag("EMBED");
            for(Element id:element)
            {
                resourceweb=id.attr("src").trim();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            resourceweb="http://211.69.141.12/upload/20160223221220768.ppt";
        }
        return resourceweb;
    }
}
