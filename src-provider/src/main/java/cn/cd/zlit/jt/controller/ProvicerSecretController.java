package cn.cd.zlit.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 公共配置
 * @author: _zlit
 * @create: 2021-10-26 15:34
 **/
@RestController
@RefreshScope
public class ProvicerSecretController {
    @Value("${app.secret:123456}")
    private String secret;

    @GetMapping("provider/secret")
    public String doGetSecret(){
        return "the secret is " + secret;
    }
}
