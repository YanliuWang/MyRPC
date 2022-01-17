package com.yanliu.version1.client;

import com.yanliu.version1.common.RPCRequest;
import com.yanliu.version1.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yanliu
 * @create 2022-01-05-10:42 AM
 */

/**
 * use reflection to encapsulate a RPCRequest object
 */

@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // using builder in lombok to create a request object
        RPCRequest request = RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsType(method.getParameterTypes())
                .build();

        RPCResponse response = IOClient.sendRequest(host, port, request);
        System.out.println(response);

        return response.getContent();

    }

    <T> T getProxy(Class<T> tClass) {
        Object object = Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, this);

        return (T) object;
    }
}
