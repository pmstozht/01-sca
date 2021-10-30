package cn.cd.zlit.jt.common.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * @description: 模拟一个简易浏览器
 * @author: _zlit
 * @create: 2021-10-25 10:37
 **/
public class Browser {
    public static void main(String[] args) throws IOException {
        //1.创建网络编程中的客户端对象(Socket)
        //构建Socket对象时要执行连接计算机(ip),访问计算中的哪个应用(port)
        Socket socket = new Socket("127.0.0.1", 9999);
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = inputStream.read(buf);
        String s = new String(buf, 0, len);
        System.out.println(s);
        inputStream.close();
    }
}
