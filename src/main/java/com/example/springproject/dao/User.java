package com.example.springproject.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Entity
public class User {
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
