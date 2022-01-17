package com.yanliu.version0.server;

import com.yanliu.version0.common.User;
import com.yanliu.version0.service.UserService;

import java.util.Random;
import java.util.UUID;

/**
 * @author yanliu
 * @create 2022-01-03-11:26 PM
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUserByUserId(Integer id) {
        System.out.println("client query user:" + id + " from server");

        Random random = new Random();

        User user = User.builder().username(UUID.randomUUID().toString())
                .id(id)
                .gender(random.nextBoolean()).build();

        return user;
    }
}
