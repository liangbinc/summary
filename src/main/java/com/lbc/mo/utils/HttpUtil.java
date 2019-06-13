package com.lbc.mo.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.Charset;


public class HttpUtil {
    private static final Log LOG = LogFactory.getLog(HttpUtil.class);

    private HttpUtil() {
    }

    public static boolean httpPostWithJson(String content, String url) {
        boolean isSuccess = true;
        HttpPost post = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            post = new HttpPost(url);
            post.setHeader("Content-type", "application/json; charset=utf-8");
            StringEntity entity = new StringEntity(content, Charset.forName("UTF-8"));
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                isSuccess = false;
            } else {
                String result = EntityUtils.toString(response.getEntity());
                LOG.info(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            if (post != null) {
                post.releaseConnection();
            }
        }
        return isSuccess;
    }

    public static String httpGet(String url) {
        HttpGet get = null;
        String result = null;
        try {
            get = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpResponse response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                result = EntityUtils.toString(response.getEntity());
                LOG.info(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (get != null) {
                get.releaseConnection();
            }
        }
        return result;
    }


    public static void main(String[] args) {
        String url = "http://11.111.158.91:8080/api/v1/bills/2019-04-01%2000%3A00%3A00/2019-04-07%2023%3A59%3A59?operator_uid=1";
        String s = HttpUtil.httpGet(url);
        System.out.println(s);

    }
}
