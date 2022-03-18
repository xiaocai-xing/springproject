package com.example.springproject.bean;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.*;

/**
 * @ClassName HttpClientPost
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/4 19:02
 * @Version 1.0
 **/

@Service
public class HttpClientPost {

    //post请求方法json
    public static String dopost(String url,Map<String,String> params) {

        String result = "";
        try{
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
        try{
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

//            StringEntity stringEntity = new StringEntity(Map<String,String>params, ContentType.create(contentType,"utf-8"));
//            List<BasicNameValuePair> parammeters = new ArrayList<BasicNameValuePair>();
//            Set<String> keyset = params.keySet();
//            for (String name:keyset
//                 ) {
//                Object value = params.get(name);
//                String values = value.toString();
//               String value = params.get(name).toString();
//                parammeters.add(new BasicNameValuePair(name,values));
//            }
            StringEntity entity = new StringEntity(JSONObject.toJSONString(params), Charset.forName("UTF-8"));
            httpPost.setEntity(entity);
//            httpPost.setHeader("Content-Type","application/json");
//            httpPost.addHeader("Content-Type","application/json");
            httpresponse = httpclient.execute(httpPost);
            result = EntityUtils.toString(httpresponse.getEntity());

        }finally {
            if (httpclient != null) {
                httpclient.close();
            }
            if (httpresponse != null) {
                httpresponse.close();
            }
        }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;

    }

    //get请求方法
    public static String doget(String url,Map<String,String> params) {

        return null;
    }
    public static String doservice(String url, String type,Map<String,String> params){
        String result = "";
        if ("post".equalsIgnoreCase(type)){
            result = HttpClientPost.dopost(url,params);
        }else if ("get".equalsIgnoreCase(type)){
            result = HttpClientPost.doget(url,params);
        }
        return result;
    }
}

