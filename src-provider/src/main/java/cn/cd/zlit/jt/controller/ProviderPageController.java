package cn.cd.zlit.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-26 11:27
 **/
@RefreshScope
@RestController
public class ProviderPageController {
    @Value("${page.pageSize:10}")
    private Integer pageSize;
    @GetMapping("/provider/page/doGetPageSize")
    public String doGetPageSize(){
        //return String.format()
        return "page size is "+pageSize;
    }
}
