package com.gogo.test;

import com.gogo.rpc.api.HelloObject;
import com.gogo.rpc.api.HelloService;
import com.gogo.rpc.client.RpcClientProxy;
//import com.gogo.rpc.transport.com.gogo.rpc.client.RpcClientProxy;

/**
 * @author baj
 * @creat 2022-04-22 22:30
 */
public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);
    }
}
