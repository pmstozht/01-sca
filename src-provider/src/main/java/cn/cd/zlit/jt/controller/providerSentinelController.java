package cn.cd.zlit.jt.controller;

import cn.cd.zlit.jt.service.ResourceService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-27 14:24
 **/
@RestController
@RequestMapping("provider")
public class providerSentinelController {


    @GetMapping("sentinel01")
    public String doSentinel(){
        return "sentinel 01 test";
    }
    @GetMapping("sentinel02")
    public String doSentinel2(){
        return "sentinel 02 test";
    }

    @Autowired
    private ResourceService service;

    @GetMapping("sentinel03")
    public String doSentinel3(){
        String s = service.doGetResoutce();
        return  s;
    }

    /*提供一个AtomicLong对象,提供了线程安全的自增,自减的操作*/
    private AtomicLong atomicLong = new AtomicLong(1);
    @GetMapping("sentinel04")
    public String doSentinel4() throws InterruptedException {
        long num = atomicLong.getAndIncrement();
        if (num%2==0){
            Thread.sleep(200);
//            throw new RuntimeException("timeout");
        }
        return "sentinel 04 test ";
    }

    @GetMapping("/sentinel/findById")
    @SentinelResource("resource")
    public String doFindById(@RequestParam("id") Integer id){
        return "resource id is "+id;
    }

}