package com.yanliu.version1.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yanliu
 * @create 2022-01-04-9:11 PM
 */

/**
 * response covers different objects
 */
@Data
@Builder
public class RPCResponse implements Serializable {
    // response status information
    private String statusCode;
    private String message;

    // response content
    private Object content;

    public static RPCResponse success(Object content) {
        return RPCResponse.builder().statusCode("200").content(content).build();
    }

    public static RPCResponse fail() {
        return RPCResponse.builder().statusCode("500").message("server error").build();
    }



}
