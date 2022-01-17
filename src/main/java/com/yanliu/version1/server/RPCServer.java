package com.yanliu.version1.server;

import com.yanliu.version1.common.RPCRequest;
import com.yanliu.version1.common.RPCResponse;
import com.yanliu.version1.service.UserService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yanliu
 * @create 2022-01-04-9:59 PM
 */
public class RPCServer {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("server runs");

            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        ObjectOutputStream objectOutputStream =
                                new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream objectInputStream =
                                new ObjectInputStream(socket.getInputStream());

                        RPCRequest request = (RPCRequest) objectInputStream.readObject();

                        // use reflection to invoke service
                        Method method = userService.getClass().getMethod(request.getMethodName(),
                                request.getParamsType());
                        Object invoke = method.invoke(userService, request.getParams());

                        // write to response object
                        objectOutputStream.writeObject(RPCResponse.success(invoke));
                        objectOutputStream.flush();


                    } catch (IOException | ClassNotFoundException | NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
