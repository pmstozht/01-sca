package cn.cd.zlit.jt.service;

import cn.cd.zlit.jt.factory.ProviderFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*FeignClient 注解描述的接口,用于定义远程调用规范
*其中,name属性的值为远端服务名,同时也会将这个名字作为
* RemoteProviderService接口实现类的Bean对象名
* */

@FeignClient(name = "sca-provider",fallbackFactory = ProviderFallbackFactory.class)
public interface RemoteProviderService {
    /*基于此方法进行远程服务调用*/

    /*Feign接口是基于方法上的@GetMapping等注解中国的value属性值,
    * 后续会基于这个值调用远端服务的具体方法
    * */
    @GetMapping("/provider/echo/{msg}")
    String echoMessage(@PathVariable("msg") String msg);
}
