package cn.cd.zlit.jt.controller;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:自定义异常处理对象
 * @author: _zlit
 * @create: 2021-10-28 10:31
 **/
@Component
public class ServiceBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        //设置响应数据的编码
        response.setCharacterEncoding("utf-8");
        //告诉客户端响应数据的类型,以及客户端显示内容的编码
        response.setContentType("text/html;charset=utf-8");
        //向客户端响应一个json格式的字符串
        Map<String,Object> map = new HashMap<>();
        map.put("status", 429);
        map.put("message", "访问太频繁了");
        String s = new ObjectMapper().writeValueAsString(map);
        PrintWriter out = response.getWriter();
        out.print(s);
        out.flush();
        out.close();
    }
}
