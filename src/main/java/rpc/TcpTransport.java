package rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TcpTransport {

    private String host;
    private int port;

    public TcpTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket createSocket(){
        Socket socket = null;
        try {
            socket = new Socket(host,port);
            return socket;
        } catch (Exception e) {
            throw  new RuntimeException("建立新的socket客户端连接失败",e);
        }
    }

    public Object send(RpcRequest rpcRequest){
        Socket socket = null;
        try{
            socket = createSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();
            objectInputStream.close();
            objectOutputStream.close();
            return result;

        }catch (Exception e){
            throw new RuntimeException("发送客户端连接失败");
        }finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
