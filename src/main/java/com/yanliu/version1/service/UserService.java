package com.yanliu.version1.service;

import com.yanliu.version0.common.User;

/**
 * @author yanliu
 * @create 2022-01-04-9:23 PM
 */
public interface UserService {
    User getUserByUserId(Integer id);
    Integer insertUserId(User user);
}
