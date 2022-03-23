package com.lyh.test;

import com.lyh.pojo.User;
import com.lyh.service.impl.UserServiceImpl;
import org.junit.Test;

public class UserServiceImplTest {
    UserServiceImpl userService = new UserServiceImpl();
    @Test
    public void register() {
        userService.registUser(new User(null,"test1","123","test1@qq.com"));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "test1", "123", "test1@qq.com")));
    }

    @Test
    public void existUsername() {
        if(userService.existsUsername("test11")) System.out.println("用户名已存在");
        else System.out.println("用户名可用");
    }
}