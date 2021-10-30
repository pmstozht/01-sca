package cn.cd.zlit.jt;

import com.google.common.base.Supplier;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:定时任务案例
 * @author: _zlit
 * @create: 2021-10-26 10:25
 **/
public class TimerUtils {
    public static void main(String[] args) {
//        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);
//        for (int i = 0 ; i < 100;i++) {
//            executor.schedule(new timerTask2(),100*i, TimeUnit.MILLISECONDS);
//        }
//        executor.shutdown();
////        while(!executor.isTerminated()){};
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
        ses.scheduleAtFixedRate(new timerTask2(), 0, 100, TimeUnit.MILLISECONDS);
        ses.scheduleAtFixedRate(new timerTask2(), 0, 100, TimeUnit.MILLISECONDS);
    }

    public static void timerTask() {
//        1.
        Timer timer = new Timer();
//        2.创建任务
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis());
//                timer.cancel();结束任务调度
            }
        };
//        3.定时调度任务
        timer.schedule(task1, 1000, 1000);
        //基于Timer类执行定时任务时,最大的缺陷时多个任务不能并发执行
//       基于ScheduledExecutorService接口的实现类去实现多线程的任务调度
//       在nacos注册中心发送定时心跳以及nacos配置中心定时拉取,底层都是通过这个对象完成的

    }

}

class timerTask2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : " + System.currentTimeMillis());
    }
}