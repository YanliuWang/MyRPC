package com.yanliu.version0.client;

import com.yanliu.version0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

/**
 * @author yanliu
 * @create 2022-01-03-8:29 PM
 */
public class RPCClient {
    public static void main(String[] args) {
        try {
            // build socket connection
            Socket socket = new Socket("127.0.0.1", 8899);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            // request from client to server
            // client sends userId to server
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();

            // response from server to client
            // server sends user to client
            User user = (User) objectInputStream.readObject();
            System.out.println("queried User from server");
            System.out.println(user);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
