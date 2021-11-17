package com.example.springproject.service;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Entity
public class User {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column
    private String username;
    @Column
    private String userpassword;
    @Column
    private String sex;
    @Column
    private int age;

    //用户初始密码
    private String ADD_PASSWORD="123456";



    public String getPassword() {
        return userpassword;
    }

    public void setPassword(String userpassword) {
        this.userpassword = userpassword;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {

        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getADD_PASSWORD(){
        return ADD_PASSWORD;
    }
    public void setADD_PASSWORD(String ADD_PASSWORD){
        this.ADD_PASSWORD=ADD_PASSWORD;
    }



//    @Override
//    public String toString() {
//        return "user{" +
//                "id=" + id +
//                ", name='" + username + '\'' +
//                ", password='" + userpassword + '\'' +
//                ", age=" + age +
//                ", sex='" + sex + '\'' +
//                '}';
//    }

}
