package com.lbc.corgi;

import com.lbc.mo.utils.HttpUtil;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MailTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("changlb1@qq.com");
        Map<String, Object> map = new HashMap<>(4);
        map.put("sender", "ai-master-noreply@qq.com");
        map.put("recipients", list);
        map.put("subject", "test title");
        map.put("content", "test");
        System.out.println("map :" + JSONObject.fromObject(map).toString());

//        String s ="{\"sender\":\"ai-master-noreply@qq.com\",\"recipients\":[\"changlb1@qq.com\"],\"subject\":\"test title\",\"content\":\"test\"}";
//        System.out.println(JSONObject.fromObject(map).toString());
        String url = "http://11.111.158.99:10025/mail";
        HttpUtil.httpPostWithJson(JSONObject.fromObject(map).toString(), url);

    }
}
