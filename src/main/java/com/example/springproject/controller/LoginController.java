package com.example.springproject.controller;

import com.example.springproject.dao.ReponseInfo;
import com.example.springproject.dao.ResultInfo;
import com.example.springproject.dao.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName LoginController
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/2 10:02
 * @Version 1.0
 **/

@Controller
public class LoginController extends ReponseInfo {
    @RequestMapping("/")
    public String Login(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(){ return "index"; }

    @RequestMapping("/LoginOut")
    public String LoginOut(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();
        sessionStatus.setComplete();
        return "redirect:/";
    }

    private UserDao userServices;
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

}
