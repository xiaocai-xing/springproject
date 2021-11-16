package com.example.springproject.controller;

import com.example.springproject.dao.ReponseInfo;
import com.example.springproject.dao.ResultInfo;
import com.example.springproject.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LoginMod
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/3 11:28
 * @Version 1.0
 **/
@RestController
@ResponseBody

public class LoginMod extends ReponseInfo {

    @Autowired
    private UserDao userServices;

    @Autowired
    public void setUserService(UserDao userService) {
        this.userServices = userService;
    }


    @RequestMapping("/login/account")
    public ResultInfo LoginAccount(HttpServletRequest request, HttpSession session) {
        ResultInfo result = new ResultInfo();
        String Username = request.getParameter("username");
        String Userpassword = request.getParameter("userpassword");
        String ResultUser = userServices.QueryUser(Username);
        if (ResultUser.equals("1")) {
            String ResultModPassword = userServices.QueryUserPass(Username, Userpassword);
            if (ResultModPassword.equals("1")) {
                session.setAttribute("username", Username);
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getACCOUNT_SUCCESS());
            } else {
                result.setCode(getFAIL_CODE());
                result.setErrormsg(getACCOUNT_ERROR());
            }
        } else {
            result.setCode(getFAIL_CODE());
            result.setErrormsg(getACCOUNT_NO_FOUND());
        }
        return result;
    }

    @RequestMapping("/checkUser")
    public ResultInfo checkUser(HttpServletRequest request, HttpServletResponse response) {
        //编码规范
        ResultInfo result = new ResultInfo();
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        //获取session值
        Map<String,Boolean> xcmap = new HashMap<>();
        boolean XX = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("username").equals("admin")){
            XX= true;
        }
        xcmap.put("XX",XX);
        result.setCode(getSUCCESS_CODE());
        result.setMsg(getACCOUNT_SUCCESS());
        result.setData(xcmap);
        return result;
    }
}
