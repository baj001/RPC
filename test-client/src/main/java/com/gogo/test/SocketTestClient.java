package com.gogo.test;

import com.gogo.rpc.RpcClientProxy;
import com.gogo.rpc.api.HelloObject;
import com.gogo.rpc.api.HelloService;
import com.gogo.rpc.socket.SocketClient;

/**
 * 测试用消费者（客户端）
 * @author ziyang
 */
public class SocketTestClient {

    public static void main(String[] args) {
        SocketClient client = new SocketClient("127.0.0.1", 9000);
        RpcClientProxy proxy = new RpcClientProxy(client);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}