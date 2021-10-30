package cn.cd.zlit.common.interceptor;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description: 尝试自定义框架
 * @author: _zlit
 * @create: 2021-10-28 16:17
 **/

/*
如何理解框架?设计好的一个半成品
* 框架设计时会有一些对象的定义以及这些对象的执行流程,类似一个执行链
* */

//拦截器接口
interface HandlerInterceptor {
    default void before() {
    }

    default void after() {
    }
}

//处理器接口
interface Handler {
    void proessed();//处理业务的方法
}


//定义一个执行连接
class ExecutionChain {
    //一些拦截器
    private List<HandlerInterceptor> interceptors = new CopyOnWriteArrayList<>();
    //业务处理器
    private Handler handler;

    public ExecutionChain(List<HandlerInterceptor> interceptors,Handler handler){
        this.handler = handler;
        this.interceptors.addAll(interceptors);
    }
    public void execute() {//负责执行业务的方法(例如处理请求)
        for (int i = 0; i < interceptors.size(); i++) {
            interceptors.get(i).before();
        }
        handler.proessed();;
        for (int i = interceptors.size() - 1; i >= 0;i--) {
            interceptors.get(i).after();
        }
    }
}
//应用框架
public class FramewordTests {
    public static void main(String[] args) {
        HandlerInterceptor myInterceptor1 = new MyInterceptor("何广的牛子");
        HandlerInterceptor myInterceptor2 = new MyInterceptor("何广的鸭儿");
        HandlerInterceptor myInterceptor3 = new MyInterceptor("何广的二头子");
        CopyOnWriteArrayList<HandlerInterceptor> objects = new CopyOnWriteArrayList<>();
        objects.add(myInterceptor1);
        objects.add(myInterceptor2);
        objects.add(myInterceptor3);
        ExecutionChain chain = new ExecutionChain(objects,new MyHandler());
        chain.execute();
    }
}

class MyInterceptor implements HandlerInterceptor{
    private String name;
    public MyInterceptor(String name){
        this.name = name;
    }

    @Override
    public void before() {
        System.out.println(name+",before");
    }

    @Override
    public void after() {
        System.out.println(name+",after");
    }
}

class MyHandler implements Handler{
    @Override
    public void proessed() {
        System.out.println("成都何广");
    }
}