package com.example.springproject.dao;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName ResultInfo
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/2 10:31
 * @Version 1.0
 **/
public class ResultInfo {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //响应状态
    private String code;

    //总数
    private Integer count;

    //响应错误消息
    private String msg;

    //响应错误消息
    private  String errormsg;

    //响应业务参数
    private Object data;

    public static ObjectMapper getMapper(){
        return MAPPER;
    }

    public Integer getCount(){
        return count;
    }

    public void setCount(Integer count){
        this.count=count;
    }

    public String getCode(){
        return code;
    }

    public void setCode(String code){
        this.code=code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public String getErrormsg(){
        return errormsg;
    }

    public void setErrormsg(String errormsg){
        this.errormsg=errormsg;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data=data;
    }
}
