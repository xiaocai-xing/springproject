package com.example.springproject.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.springproject.dao.ReponseInfo;
import com.example.springproject.dao.ResultInfo;
import com.example.springproject.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDataController
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/3 11:56
 * @Version 1.0
 **/

@RestController
@ResponseBody
public class UserDataController extends ReponseInfo {
    private UserDao userServices;
    @Autowired
    public void setUserService (UserDao userService) {
        this.userServices = userService;
    }

    @RequestMapping("/query/userdata")
    public ResultInfo GetUser(HttpServletRequest request){

        ResultInfo result = new ResultInfo();

        String page = request.getParameter("page");
        int pages = Integer.parseInt(page);
        String limit = request.getParameter("limit");
        int limits = Integer.parseInt(limit);

        List<Map<String, Object>> select;
        List<Object> listData = new ArrayList<>();


        select = userServices.QueryUserData(pages,limits);
        Integer count = userServices.UserCount();

        result.setCode(getSUCCESS_CODE());
        result.setCount(count);
        result.setMsg(getACCOUNT_SUCCESS());
        result.setErrormsg("");

        for (Map<String, Object> UserDatum : select) {
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(UserDatum);
            listData.add(jsonObject);
        }
        result.setData(listData);
        return result;
    }

    @RequestMapping("/add/userinfo")
    public ResultInfo adduser(HttpServletRequest request, HttpSession session){

        ResultInfo result = new ResultInfo();
        Map<String,String> TeMap = new HashMap<>();

        String sessionUsername = (String) session.getAttribute("username");
        if (!sessionUsername.equals("admin")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getNOT_ADMIN());
            return result;
        }


        TeMap.put("username",request.getParameter("username"));
        TeMap.put("userpassword",request.getParameter("userpassword"));
        TeMap.put("age",request.getParameter("age"));
        TeMap.put("sex",request.getParameter("sex"));


        String name = userServices.QueryUser(request.getParameter("username"));

        if(name.equals("0")){
            Boolean res = userServices.adduser(TeMap);
            if(res.equals(true)){
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
                result.setErrormsg("");
            }else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
            }

        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT__FOUNDED());
        }
        return result;

    }

    @RequestMapping("/mod/userinfo")
    public ResultInfo moduser(@RequestBody JSONObject User, HttpSession session){

        ResultInfo result = new ResultInfo();
        String sessionUsername = (String) session.getAttribute("username");
        if (!sessionUsername.equals("admin")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getNOT_ADMIN());
            return result;
        }

        Map<String,String> TeMap = new HashMap<>();
        TeMap.put("username", User.getString("name"));
        TeMap.put("userpassword",User.getString("userpassword"));

        String ResultMod = userServices.QueryUserPass(User.getString("name"),
                User.getString("userpassword"));
        if(ResultMod.equals("1")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getEDITACCOUNT_NEWOLD_SAME());
            return result;
        }

        String count = userServices.QueryUser(User.getString("name"));
        if(count.equals("1")){
            Boolean res = userServices.ModUser(TeMap);
            if(res.equals(true)){
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
                result.setErrormsg("");
            }else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
            }

        }else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT__FOUNDED());
        }
        return result;

    }

    @RequestMapping("/del/userinfo")
    public ResultInfo DelUser(@RequestBody JSONObject data, HttpSession session){

        ResultInfo result = new ResultInfo();

        String sessionUsername = (String) session.getAttribute("username");
        if (!sessionUsername.equals("admin")){
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getNOT_ADMIN());
            return result;
        }

        JSONArray User;
        User = data.getJSONArray("name");

        boolean res = userServices.DelUser(User);
        if(res){
            result.setCode(getSUCCESS_CODE());
            result.setMsg(getACCOUNT_SUCCESS());
            result.setErrormsg("");
            return result;
        }
        result.setCode(getFAIL_CODE());
        result.setErrormsg(getACCOUNT_DELERROR());
        return result;
    }
}
