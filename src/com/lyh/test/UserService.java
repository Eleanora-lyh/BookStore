package com.lyh.test;

import com.lyh.pojo.User;

public interface UserService {
    /**
     * @Description 注册一个用户
     * @Param [user]
     * @return void
     **/
    public void register(User user);

    /**
     * @Description 登录用户
     * @Param
     * @return
     **/
    public User login(User user);
    
    /**
     * @Description 检查用户名是否可用
     * @Param 
     * @return 返回true已存在，返回false表示可用
     **/
    public boolean existUsername(String username);
}
