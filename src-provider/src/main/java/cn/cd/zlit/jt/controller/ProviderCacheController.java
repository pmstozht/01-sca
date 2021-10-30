package cn.cd.zlit.jt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-26 11:28
 **/
@RefreshScope
@RestController
public class ProviderCacheController {
    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    @RequestMapping("/provider/cache")
    public String doUseLocalCache() {
        return "useLocalCache'value is   " + useLocalCache;
    }

    //构建一个本地缓存对象(基于JVM中的一个对象存储从数据库获取的数据)
    //CopyOnWriteArrayList是一个线程安全的list集合,使用了乐观锁(CAS算法_比较和交换)
    // 乐观锁 : 允许多个线程执行并发更新,但是只能有一个更新成功
    //再使用双重逻辑校验,则可以再保证安全的同时也可以保证其性能
    private List<String> cache = new CopyOnWriteArrayList<>();
    @RequestMapping("/provider/cache02")
    public List<String> doUseLocalCache02() {
        if (!useLocalCache) {
            System.out.println("==Get date from database==");
            return Arrays.asList("A", "B", "C");
        }
        if (cache.isEmpty()) {
            synchronized (cache) {
                if (cache.isEmpty()) {
                    System.out.println("==Get date from database==");
                    //假如这部分分类是从数据库取,但是,我不希望每次获取分类都是从数据库
                    List<String> list = Arrays.asList("A", "B", "C");
                    cache.addAll(list);
                }
            }
        }
        return cache;
    }
}