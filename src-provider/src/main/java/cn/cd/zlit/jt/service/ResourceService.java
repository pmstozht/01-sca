package cn.cd.zlit.jt.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-27 15:41
 **/


@Service
public class ResourceService {

    /*使用@SentinelResource注解描述的方法
     * 在此方法被访问时,会在sentinel的簇点链路中显示,
     * 此注解中指定的名字就是资源名,我们可以对这个资源的访问
     * 按照指定的链路进行限流设计*/
    /*此注解中的blockHandlerClass用于指定,出现限流异常时的异常处理类,
    * blockHandler属性用于指定异常处理类中的方法(此方法的返回类型,参数
    * 要与@SentinelResource注解描述的方法一致,可以加异常参数类型
    * ,并且必须是静态方法)*/
    /*fallbackClass 用于指定业务异常处理类,fallback用于指向业务处理类中
    * 的异常处理方法(此方法的返回类型,参数要与@SentinelResource注解描述的
    * 方法一致,可以加Throwable异常参数类型)*/
    @SentinelResource(value = "doGetResource",blockHandlerClass = ResourceBlockHandler.class,blockHandler = "rinigezi")
    public String doGetResoutce() {
        return "do get resource";
    }
}