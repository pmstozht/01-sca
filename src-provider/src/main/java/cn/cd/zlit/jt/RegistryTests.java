package cn.cd.zlit.jt;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-27 10:05
 **/
public class RegistryTests {

    private static Map<String,String> registryMap =  new HashMap<>();
    private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void doRegist(String serviceId,String host){
        rwl.writeLock().lock();//悲观写
        registryMap.put(serviceId, host);
    }

    public static String doLookUp(String serviceId){
        rwl.readLock().lock();//乐观读
        return registryMap.get(serviceId);
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 100; i++) {
                    doRegist(i+"", i+1000+"");
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run(){
                for (int i = 0; i < 100; i++) {
                    System.out.println(i+" : "+doLookUp(i+""));
                }
            }
        };
        t1.start();
        t2.start();
    }
}
