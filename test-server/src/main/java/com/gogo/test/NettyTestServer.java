package com.gogo.test;

import com.gogo.rpc.api.HelloService;
import com.gogo.rpc.netty.server.NettyServer;
import com.gogo.rpc.registry.DefaultServiceRegistry;
import com.gogo.rpc.registry.ServiceRegistry;

/**
 * 测试用Netty服务提供者（服务端）
 * @author ziyang
 */
public class NettyTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.start(9999);
    }

}