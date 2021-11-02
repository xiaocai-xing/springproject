package com.example.springproject.dao;


/**
 * @ClassName User
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/2 10:12
 * @Version 1.0
 **/
public class User {

    private long id;

    private String username;

    private String userpassword;

    private String sex;

    private int age;

    public long getId() {
        return id;
    }

    public String getPassword() {
        return userpassword;
    }

    public void setPassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public void setId(long id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + username + '\'' +
                ", password='" + userpassword + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                '}';
    }

}
