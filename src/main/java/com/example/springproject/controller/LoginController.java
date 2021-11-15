package com.example.springproject.controller;

import com.example.springproject.dao.ReponseInfo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

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

}
