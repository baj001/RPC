package com.gogo.test;

import com.gogo.rpc.api.HelloService;
import com.gogo.rpc.server.RpcServer;

/**
 * @author baj
 * @creat 2022-04-22 22:34
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }
}
