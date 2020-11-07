package com.apray.blog.service;

import com.apray.blog.po.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User checkUser(String username, String password);
}
