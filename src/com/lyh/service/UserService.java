package com.lyh.service;

import com.lyh.pojo.User;

public interface UserService {
    public void registUser(User user);
    public User login(User user);
    public boolean existsUsername(String username);
}
