package com.example.springproject.bean;


import com.example.springproject.dao.ResultInfo;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
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

    //post请求方法json
    public String dopost_js(String url, String content,String params) {

        String result = null;
        String contentType = content;
        try{
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
        try{
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);

            StringEntity stringEntity = new StringEntity(params, ContentType.create(contentType,"utf-8"));
            httpPost.setEntity(stringEntity);
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
    public String doget(String url,String key){
        CloseableHttpClient closeableHttpClient = null;
        CloseableHttpResponse response = null;
        String result = null;
        ResultInfo resultInfo = new ResultInfo();

        try {
            closeableHttpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("access_token",key);

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
