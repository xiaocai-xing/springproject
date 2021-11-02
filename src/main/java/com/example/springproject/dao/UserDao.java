package com.example.springproject.dao;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author Charles0219
 * @Date 2021/11/2 10:16
 * @Version 1.0
 **/
public class UserDao extends User{
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate (JdbcTemplate JdbcTemplate){
        this.jdbcTemplate = JdbcTemplate;
    }
    private UserDao userDao;

    public void setUserDao (UserDao UserDao){
        this.userDao = UserDao;
    }
    /*用户名查询*/
    public String QueryUser(String username){

        String Key ="SELECT COUNT(*) FROM `userinfo` WHERE user_name =? ;";
        return jdbcTemplate.queryForObject(Key,String.class,username);
    }

    /*用户名和密码查询 */
    public String QueryUserPass(String username,String userpassword){

        String Key ="SELECT COUNT(*) FROM `userinfo` WHERE user_name=? AND user_passwd=?;";
        return jdbcTemplate.queryForObject(Key,String.class,username,userpassword);
    }

    /*修改密码 */
    public boolean ModUser(Map<String, String> UserData){

        String Key = "UPDATE `userinfo` SET `user_passwd`=? WHERE `user_name` = ?;";
        return jdbcTemplate.update(Key,UserData.get("userpassword")
                ,UserData.get("username"))>0;

    }

    /*查询用户数据 */
    public List<Map<String, Object>> QueryUserData(int page, int limit){

        String Key = "SELECT * FROM `userinfo` LIMIT ? , ?;";
        return jdbcTemplate.queryForList(Key,(page-1)*limit,page*limit);

    }

    /*查询用户数据总数 */
    public Integer UserCount(){

        String Key = "SELECT count(*) from `userinfo`";
        return jdbcTemplate.queryForObject(Key,Integer.class);

    }

    /*添加用户数据 */
    public boolean adduser(Map<String, String> UserData){

        String Key = "INSERT into`user_info` (`username`,`userpassword`,`age`,`sex`) VALUES(?,?,?,?);";
        return jdbcTemplate.update(Key,UserData.get("username"),UserData.get("userpassword"),UserData.get("age"),UserData.get("sex"))>0;

    }


    /*删除用户数据 */
//    public boolean DelUser(JSONArray User){
//
//        for (Object name : User) {
//            String Key = "DELETE FROM `user_info` WHERE `username`=? ;";
//            if (jdbcTemplate.update(Key,name) == 0) {
//                return false;
//            }
//        }
//        return true;
//
//    }

}
