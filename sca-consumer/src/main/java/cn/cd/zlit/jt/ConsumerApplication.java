package cn.cd.zlit.jt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-21 15:37
 **/

/*当时勇士EnableFeginClients注解描述配置类时,主要
  用于告诉Spring框架,要对使用@FeignClient注解描述
  的接口创建其实现类以及对象
* */

@EnableFeignClients
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

    /*
    * 在Spring中配置第三方bean时,可以通过@Bean的方式
    * 对指定方式进行描述,然后在方法内部进行对象的创建和配置,
    * 这个bean一个默认的名字,默认为方法名,这个名字对应bean的
    * 作用域为单例作用域
    * */

    @Bean
    public RestTemplate restTemplate(){
        return new  RestTemplate();
    }

    /*使用@loadBalanced注解描述RestTemplate对象时
     * 假如使用RestTemplate对象发起远程服务调用,底层会对这个
     * 请求进行拦截,拦截到此请求后,会基于LoadBalancerClient对象
     * 获取服务实例,然后进行负载均衡方式的调用*/
    @Bean
    @LoadBalanced
    public RestTemplate loadBalancedRestTemplate(){
        return new RestTemplate();
    }

    @RestController
    public class ConsumerController{
        @Value("${spring.application.name}")
        private String appName;

        /*从spring容器中获取一个RestTemplate对象
        *  调用远端的sca-provider中的服务 */
        @Autowired
        private RestTemplate restTemplate;

        @Autowired
        private LoadBalancerClient loadBalancerClient;

        @GetMapping("/consumer/doRestEcho1")
        public  String doRestEch01(){
            String url = "http://localhost:8081/provider/echo/"+appName;
            System.out.println("request url:"+url);
            return restTemplate.getForObject(url, String.class);
        }

        @GetMapping("/consumer/doRestEcho02")
        public  String doRestEch02() {
            //1.获取privoder服务实例
            ServiceInstance serviceInstance = loadBalancerClient.choose("sca-provider");
            String url = String.format("http://%s:%s/provider/echo/%s", serviceInstance.getHost(), serviceInstance.getPort(), appName);
            System.out.println("request url"+url);
            return restTemplate.getForObject(url, String.class);
        }

        @Autowired
        private RestTemplate loadBalancedRestTemplate;

        @GetMapping("/consumer/doRestEcho03")
        public  String doRestEch03() {
            //1.获取privoder服务实例
            String url = "http://sca-provider/provider/echo"+appName;
            System.out.println("request url"+url);
            return loadBalancedRestTemplate.getForObject(url, String.class);
        }

    }
}
