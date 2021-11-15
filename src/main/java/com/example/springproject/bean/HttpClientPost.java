package com.example.springproject.bean;


import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName HttpClientPost
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/4 19:02
 * @Version 1.0
 **/

@Service
public class HttpClientPost {


    //post请求方法key-value
    public String doPost_key(String url, String method, String content, Map<String, Object> paramMap) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost post = new HttpPost(url);
        String result = "";


        try (CloseableHttpClient closeableHttpClient = httpClientBuilder.build()) {

            //设置参数
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            for (Iterator iter = paramMap.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String value = String.valueOf(paramMap.get(name));
                nvps.add(new BasicNameValuePair(name, value));
            }
            HttpEntity entity = new StringEntity(nvps.toString(), "UTF-8");
            post.setEntity(entity);
            //设置请求头
            String header = content;
            post.getHeaders(header);

            //返回体
            HttpResponse resp = closeableHttpClient.execute(post);
            int code = resp.getStatusLine().getStatusCode();
            if (code == 200) {
                try {
                    InputStream respIs = resp.getEntity().getContent();
                    byte[] respBytes = IOUtils.toByteArray(respIs);
                    result = new String(respBytes, Charset.forName("UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("状态码：" + code);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    //post请求方法json
    public String dopost_js(String url, String method, String content,String params) {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        HttpPost post = new HttpPost(url);
        String result = "";
        //设置请求头
        String accept = content;
        post.setHeader("Accept",content);
        String header = content;
        post.setHeader("Content-Type",content);
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        post.setEntity(entity);

        try (CloseableHttpClient closeableHttpClient = httpClientBuilder.build()){
            HttpResponse resp = closeableHttpClient.execute(post);
            StatusLine status = resp.getStatusLine();
            int state = status.getStatusCode();
            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = resp.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                System.out.println("code" + state);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }



    //get请求方法
    public String doget(String url){
        CloseableHttpClient closeableHttpClient = null;
        CloseableHttpResponse response = null;
        String result = null;

        try {
            closeableHttpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);

            response = closeableHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null !=response){
                try {
                    response.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }if (null !=closeableHttpClient){
                try {
                    closeableHttpClient.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


}
