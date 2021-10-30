package cn.cd.zlit.jt.factory;

import cn.cd.zlit.jt.service.RemoteProviderService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @description:远程调用回调工厂
 * @author: _zlit
 * @create: 2021-10-25 09:29
 **/
@Component
public class ProviderFallbackFactory implements FallbackFactory {
    @Override
    public RemoteProviderService create(Throwable throwable) {
        return new RemoteProviderService() {
            @Override
            public String echoMessage(String msg) {
                return "你很机车欸";
            }
        };
    }
}
