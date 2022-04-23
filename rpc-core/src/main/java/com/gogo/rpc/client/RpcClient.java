package com.gogo.rpc.client;

import com.gogo.rpc.entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 具体的远程api调用逻辑利用RpcClient对象来实现，
 * 这个对象的作用，就是将一个对象发过去，并且接受返回的对象
 * readObject()方法就可以获得一个返回的对象
 * @author baj
 * @creat 2022-04-22 21:31
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
            //将输出流的数据写入request请求，返回输入流读取到的数据
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();
            //readObject()方法就可以获得一个返回的对象
            return objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：" + e);
            return null;
        }
    }
}

