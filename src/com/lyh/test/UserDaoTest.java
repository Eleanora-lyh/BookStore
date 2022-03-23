package com.lyh.test;

import com.lyh.dao.UserDao;
import com.lyh.dao.impl.UserDaoImpl;
import com.lyh.pojo.User;
import org.junit.Test;

public class UserDaoTest {
    UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByName() {
        if(userDao.queryUserByName("admin")==null)
            System.out.println("用户名可用");
        else System.out.println("用户名已存在");
    }

    @Test
    public void queryUserByNameAndPassword() {
        if(userDao.queryUserByNameAndPassword("admin是的","admin")==null){
            System.out.println("用户名或密码错误，登陆失败");
        }else{
            System.out.println("登陆成功");
        }
    }

    @Test
    public void saveUser() {
        System.out.println(userDao.SaveUser(new User(2, "admin123", "admin123", "admin123@lyh.com")));
    }
}