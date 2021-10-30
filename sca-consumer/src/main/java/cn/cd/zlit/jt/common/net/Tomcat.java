package cn.cd.zlit.jt.common.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-25 10:20
 **/
public class Tomcat {
    public static void main(String[] args) throws IOException {
        //1.创建服务(例如:启动nacos,启动...),并在9999端口进行监听
        //网络中计算机的唯一标识是什么?ip
        //计算机中应用程序的唯一标识是什么?port
        ServerSocket server = new ServerSocket(9999);
        System.out.println("server start...");
        while (true){
            //监听客户端的连接
            Socket socket = server.accept();//阻塞方法->没有连接的时候就不要循环
            //在这里可以将socket对象的信息记录一下(服务注册)
//            System.out.println("连接已建立");
//            输出流对象,向客户端输出hello client
            OutputStream outputStream = socket.getOutputStream();
            byte[] responseContext = ("HTTP/1.1 200 ok \r\n"+
                    "Content-Type: text/html;charset=utf-8 \r\n"+
                    "\r\n"+
                    "<h1>嗦牛子</h1>").getBytes();
            outputStream.write(responseContext);
            outputStream.flush();
        }
    }
}
