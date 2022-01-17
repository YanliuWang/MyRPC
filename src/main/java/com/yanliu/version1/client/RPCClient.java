package com.yanliu.version1.client;


import com.yanliu.version1.common.User;
import com.yanliu.version1.service.UserService;

/**
 * @author yanliu
 * @create 2022-01-05-10:56 AM
 */
public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1", 8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("user is" + userByUserId);

        User user = User.builder().username("Leo").id(100).gender(true).build();
        Integer insertUserId = proxy.insertUserId(user);
        System.out.println("insert user id is:" + insertUserId);
    }
}
