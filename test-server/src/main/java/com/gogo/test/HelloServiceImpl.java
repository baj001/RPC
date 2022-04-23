package com.gogo.test;

import com.gogo.rpc.api.HelloObject;
import com.gogo.rpc.api.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author baj
 * @creat 2022-04-22 19:28
 */
public class HelloServiceImpl implements HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello(HelloObject object) {
        logger.info("接收到：{}", object.getMessage());
        return "这是调用的返回值，id=" + object.getId();
    }

}