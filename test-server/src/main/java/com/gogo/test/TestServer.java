package com.gogo.test;

import com.gogo.rpc.api.HelloService;
import com.gogo.rpc.registry.DefaultServiceRegistry;
import com.gogo.rpc.registry.ServiceRegistry;
import com.gogo.rpc.server.RpcServer;

/**
 * @author baj
 * @creat 2022-04-22 22:34
 */
public class TestServer {
    public static void main(String[] args) {
        //创建服务对象
        HelloService helloService = new HelloServiceImpl();
        //创建服务容器
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        //注册服务对象到服务容器中
        serviceRegistry.register(helloService);
        //将服务容器纳入到服务端
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        //启动服务端
        rpcServer.start(9000);
    }
}
