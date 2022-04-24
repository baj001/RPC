package com.gogo.rpc;

/**
 * 具体的远程api调用逻辑利用RpcClient对象来实现，
 * 这个对象的作用，就是将一个对象发过去，并且接受返回的对象
 * readObject()方法就可以获得一个返回的对象
 * @author baj
 * @creat 2022-04-22 21:31
 */

import com.gogo.rpc.entity.RpcRequest;

/**
 * 客户端类通用接口
 * @author ziyang
 */
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

}

