package com.gogo.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @author baj
 * @creat 2022-04-22 22:18
 */
public class RpcServer {

    private final ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    public RpcServer(){
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        /**
         * 设置上限为100个线程的阻塞队列
         */
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        /**
         * 创建线程池实例
         */
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    public void register(Object service, int port){
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器正在启动……");
            Socket socket;
            //当未接收到连接请求时，accept()会一直阻塞
            while ((socket = serverSocket.accept()) != null){
                logger.info("客户端连接！IP：" + socket.getInetAddress());
                threadPool.execute(new WorkerThread(socket, service));
            }
        }catch (IOException e){
            logger.info("连接时有错误发生：" + e);
        }
    }

}
