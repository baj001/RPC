package com.gogo.rpc.enumeration;

/**
 * @author baj
 * @creat 2022-04-23 21:47
 */

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RPC调用过程中的错误
 * @author ziyang
 */
/**
 * @author [PANDA] 1843047930@qq.com
 * @date [2021-02-07 18:48]
 * @description Rpc调用过程中出现的错误
 */
@AllArgsConstructor
@Getter
public enum RpcError {

    SERVICE_INVOCATION_FAILURE("服务调用出现失败"),
    SERVICE_CAN_NOT_BE_NULL("注册的服务不能为空"),
    SERVICE_NOT_FOUND("找不到对应的服务"),
    SERVICE_NOT_IMPLEMENT_ANY_INTERFACE("注册的服务未实现接口");

    private final String message;
}