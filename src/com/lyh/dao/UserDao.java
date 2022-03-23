package com.lyh.dao;
import com.lyh.pojo.User;
/**
 * @Description 在BaseDao的基础上定义了User接口需要实现的方法
 * @Param Username
 * @return
 **/
public interface UserDao {

    //根据用户名查找User对象
    /**
     * @Description 根据姓名返回User对象
     * @Param [name] 姓名
     * @return com.lyh.dao.User ，返回null则无该用户
     **/
    public User queryUserByName(String name);

    /**
     * @Description 根据姓名和密码返回用户信息
     * @Param  [name, password] 姓名
     * @return com.lyh.dao.User ，返回null则用户名或密码错误
     **/
    public User queryUserByNameAndPassword(String name,String password);

    /**
     * @Description 保存用户信息
     * @Param [user]
     * @return int
     **/
    public int SaveUser(User user);
}
