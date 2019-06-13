package com.lbc.mo.utils;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;
import java.util.Map;

public class HttpPostReq extends HttpPost {

    private static HttpPostReq httpPost;

    private HttpPostReq(URI uri) {
        super(uri);
    }

    public static HttpPostReq getInstance(String url, Map<String, String> param) {
        if (null == httpPost) synchronized (HttpPostReq.class) {
            if (null == httpPost) {
                try {
                    URIBuilder builder = new URIBuilder(url);
                    if (param != null) {
                        for (Map.Entry<String, String> entry : param.entrySet()) {
                            builder.addParameter(entry.getKey(), entry.getValue());
                        }
                    }
                    httpPost = new HttpPostReq(builder.build());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return httpPost;
    }
}
