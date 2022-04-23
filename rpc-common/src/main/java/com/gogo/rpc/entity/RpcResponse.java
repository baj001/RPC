package com.gogo.rpc.entity;

import com.gogo.rpc.enumeration.ResponseCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author baj
 * @creat 2022-04-22 20:43
 */
@Data
public class RpcResponse<T> implements Serializable {
    /**
     * 响应状态码
     */
    private Integer statusCode;
    /**
     * 响应状态补充信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

//    泛型中的 T 代表任意类型 “<T>”是泛型的默认值，可以被任意类型所代替
    public static <T> RpcResponse<T> success(T data) {
        //成功的时候会怎么样
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }
    public static <T> RpcResponse<T> fail(ResponseCode code) {
        //失败的时候会怎么样
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }
}

