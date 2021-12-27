package com.example.springproject.controller;


import com.example.springproject.bean.HttpClientPost;
import com.example.springproject.dao.ReponseInfo;
import com.example.springproject.dao.ResultInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String DoPostTest(HttpServletRequest request){
        String result = null;
        String url = request.getParameter("url");
        String key = request.getParameter("key");
        result = httpClientPost.doget(url,key);
        return result;
    }



}
