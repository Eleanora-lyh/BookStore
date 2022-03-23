package com.lyh.service.impl;

import com.lyh.dao.UserDao;
import com.lyh.dao.impl.UserDaoImpl;
import com.lyh.pojo.User;
import com.lyh.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.SaveUser(user);
    }

    /**
     * @return com.lyh.pojo.User
     * @Description 传入User对象，如果存在则返回User对象
     * @Param [user]
     **/
    @Override
    public User login(User user) {
        return userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
    }


    /**
     * @return boolean
     * @Description 如果用户名没有查询到，返回false，表示用户名不存在
     * @Param [username]
     */
    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByName(username) == null) return false;
        return true;
    }
}
