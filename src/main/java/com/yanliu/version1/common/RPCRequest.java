package com.yanliu.version1.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yanliu
 * @create 2022-01-04-9:05 PM
 */

/**
 * for each request we need to know the following attributes
 * the request can call lots of services
 */
@Data
@Builder
public class RPCRequest implements Serializable {
    private String interfaceName;
    private String methodName;
    private Object[] params;
    private Class<?>[] paramsType;
}
