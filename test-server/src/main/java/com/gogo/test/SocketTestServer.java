package com.gogo.test;

import com.gogo.rpc.api.HelloService;
import com.gogo.rpc.registry.DefaultServiceRegistry;
import com.gogo.rpc.registry.ServiceRegistry;
import com.gogo.rpc.socket.server.SocketServer;

/**
 * 测试用服务提供方（服务端）
 * @author ziyang
 */
public class SocketTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9000);
    }

}