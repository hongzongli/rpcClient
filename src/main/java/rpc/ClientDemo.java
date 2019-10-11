package rpc;

public class ClientDemo {


    public static void main(String[] args) {

        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        HelloService helloService = rpcClientProxy.clientProxy(HelloService.class,"localhost",8888);
        System.out.println(helloService.sayHello("hongzongli"));

    }

}
