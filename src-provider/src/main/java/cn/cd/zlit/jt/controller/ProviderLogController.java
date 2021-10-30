package cn.cd.zlit.jt.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 演示配置中心的作用，基于日志对象进行日志输出
 * @author: _zlit
 * @create: 2021-10-25 14:32
 **/
@RestController
@Slf4j
//告诉系统底层，配置中心的配置一旦发生变化，则重新构造此对象
@RefreshScope
public class ProviderLogController {
    //log对象在哪个类中创建，getLogger方法中就传入哪个类的字节码对象
//    private static Logger log =
//            LoggerFactory.getLogger(ProviderLogController.class);

    @GetMapping("provider/log/doLog01")
    public String doLog01(){
        //级别从小到大
        log.trace("===trance===");
        log.debug("===debug===");
        log.info("===info===");
        log.warn("===warn===");
        log.error("===error===");
        return "log config test";
    }
    /*
    *  请问这个配置何时读取？logLevel属性初始化时
    *  请问loglevel属性何时初始化呢？对象创建时
    *  假如希望loglevel属性值，与配置中心这个配置的值同步，怎么办？
    *  配置中心的内容一旦修改，重新构造loglevel属性
    * */
    @Value("${logging.level.cn.cd.zlit.jt:error}")
    private String logLevel;

    @GetMapping("provider/log/doLog02")
    public String doLog02(){
        log.info("log.level is {}",logLevel);
        return "log level is " + logLevel;
    }
}
