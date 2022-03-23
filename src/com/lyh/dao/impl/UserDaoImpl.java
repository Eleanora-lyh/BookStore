package com.lyh.dao.impl;

import com.lyh.dao.UserDao;
import com.lyh.pojo.User;

/**
 * @Description 具体的UserDao实现类
 * @Param
 * @return
 **/
public class UserDaoImpl extends BaseDAO implements UserDao {
    @Override
    public User queryUserByName(String name) {
        String sql = "select id,username,password,email from t_user where username = ?";
        return queryForOne(User.class,sql,name);
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(User.class,sql,name,password);
    }

    @Override
    public int SaveUser(User user) {
        String sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
