package com.lyh.pojo;
/**
 * @Description User类存储用户的信息，关联到用户注册登录模块
 * @Param
 * @return
 **/
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pwd) {
        this.password = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User(Integer id, String username, String pwd, String email) {
        this.id = id;
        this.username = username;
        this.password = pwd;
        this.email = email;
    }

    public User() {
    }
}
