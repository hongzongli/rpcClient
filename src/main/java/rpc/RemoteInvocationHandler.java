package rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {

    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);

        TcpTransport tcpTransport = new TcpTransport(host,port);

        System.out.println("发送新的远程请求，请求参数为：className="+rpcRequest.getClassName() +"  methodName="+rpcRequest.getMethodName()+" parameter="+args);

        Object result = tcpTransport.send(rpcRequest);

        return result;
    }
}
