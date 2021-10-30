package cn.cd.zlit.jt.controller;

import cn.cd.zlit.jt.service.RemoteProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-22 15:49
 **/
@RestController
@RequestMapping("consumer")
public class FeignConsumerController {
    @Autowired
    private RemoteProviderService remoteProviderService;

    @RequestMapping("echo/{msg}")
    public String doFeignEcho(@PathVariable("msg") String msg){
        return remoteProviderService.echoMessage(msg);
    }
}
