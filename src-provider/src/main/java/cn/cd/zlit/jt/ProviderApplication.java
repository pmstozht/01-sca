package cn.cd.zlit.jt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-21 14:57
 **/
@SpringBootApplication
public class ProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class,args);
    }


    @RestController
    public class ProviderController{
        @Value("${server.port}")
        private String server;
        //http://localhost:8081/provider/echo/tedu


        /*请求处理对象是通过方法处理客户端或消费端请求
        * 当前方法主要用于实现一个字符串回显,就是向客户端
        * 或服务的消费端返回一个字符串消息
        * */
        @GetMapping("/provider/echo/{msg}")
        public String doRestEcho1(@PathVariable String msg) {
            return server + " say hello " + msg;
        }
    }

}
