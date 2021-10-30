package cn.cd.zlit.common.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: 拦截器注册
 * @author: _zlit
 * @create: 2021-10-28 14:56
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TimerHandler())
                .addPathPatterns("/provider/sentinel01");
    }
//
//    @Bean
//    TimerHandler timerHandler{
//        public
//    }
}

