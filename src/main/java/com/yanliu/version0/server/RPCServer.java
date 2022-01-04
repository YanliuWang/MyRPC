package com.yanliu.version0.server;

import com.yanliu.version0.common.User;
import com.yanliu.version0.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputValidation;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yanliu
 * @create 2022-01-03-11:39 PM
 */
public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("server is working...");

            // use BIO to monitor socket
            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    try {
                        ObjectOutputStream objectOutputStream
                                = new ObjectOutputStream(socket.getOutputStream());

                        ObjectInputStream objectInputStream
                                = new ObjectInputStream(socket.getInputStream());

                        // get userId from client
                        Integer userId = objectInputStream.readInt();
                        User user = userService.getUserByUserId(userId);

                        // write user object to client
                        objectOutputStream.writeObject(user);
                        objectOutputStream.flush();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
