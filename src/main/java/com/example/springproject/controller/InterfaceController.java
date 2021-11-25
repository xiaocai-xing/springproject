package com.example.springproject.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springproject.bean.HttpClientPost;
import com.example.springproject.dao.ReponseInfo;
import com.example.springproject.dao.ResultInfo;
import com.example.springproject.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName InterfaceController
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/5 11:43
 * @Version 1.0
 **/

@RestController
@ResponseBody

public class InterfaceController extends ReponseInfo {

    private HttpClientPost httpClientPost;
    @Autowired
    public void setHttpClientPost (HttpClientPost httpClientPost) {
        this.httpClientPost = httpClientPost;
    }

    @RequestMapping("/interface/dopost")
    public ResultInfo DoPostTest(HttpServletRequest request){
        ResultInfo resultInfo = new ResultInfo();
        String re_url = request.getParameter("url");
        String request_js = request.getParameter("request");
        Map<String, Object> PostMap = new HashMap<>();
        PostMap.put("request",request.getParameter("request"));

        //判断请求方式
        String method = request.getParameter("method-name");
        String re_type = request.getParameter("type");

        if (method =="POST"){
            if (re_type=="form-data"){
                String result = httpClientPost.doPost_key(re_url,method,re_type,PostMap);
            }else if (re_type=="application/json"){
                String result = httpClientPost.dopost_js(re_url,method,re_type,request_js);
            }
        }else {
            String result = httpClientPost.doget(re_url);
        }


        return resultInfo;
    }



}
