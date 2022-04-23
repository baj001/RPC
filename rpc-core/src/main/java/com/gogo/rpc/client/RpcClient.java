package com.gogo.rpc.client;

/**
 * 具体的远程api调用逻辑利用RpcClient对象来实现，
 * 这个对象的作用，就是将一个对象发过去，并且接受返回的对象
 * readObject()方法就可以获得一个返回的对象
 * @author baj
 * @creat 2022-04-22 21:31
 */

import com.gogo.rpc.entity.RpcRequest;
import com.gogo.rpc.entity.RpcResponse;
import com.gogo.rpc.enumeration.ResponseCode;
import com.gogo.rpc.enumeration.RpcError;
import com.gogo.rpc.exception.RpcException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author [PANDA] 1843047930@qq.com
 * @date [2021-02-04 17:20]
 * @description 进行远程调用的客户端
 */
public class RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);
    public Object sendRequest(RpcRequest rpcRequest, String host, int port) {
        /**
         * socket套接字实现TCP网络传输
         * try()中一般放对资源的申请，若{}出现异常，()资源会自动关闭
         */
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            RpcResponse rpcResponse = (RpcResponse)objectInputStream.readObject();
            if(rpcResponse == null){
                logger.error("服务调用失败，service:{}" + rpcRequest.getInterfaceName());
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, "service:" + rpcRequest.getInterfaceName());
            }
            if(rpcResponse.getStatusCode() == null || rpcResponse.getStatusCode() != ResponseCode.SUCCESS.getCode()){
                logger.error("服务调用失败，service:{} response:{}", rpcRequest.getInterfaceName(), rpcResponse);
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, "service:" + rpcRequest.getInterfaceName());
            }
            return rpcResponse.getData();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：" + e);
            throw new RpcException("服务调用失败：", e);
        }
    }
}

