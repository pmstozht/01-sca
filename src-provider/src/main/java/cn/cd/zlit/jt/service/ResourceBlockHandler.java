package cn.cd.zlit.jt.service;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-28 09:44
 **/

public class ResourceBlockHandler {
    //BlockException是所有限流,降级等异常的父类异常
    public static String rinigezi(BlockException e){
        return "天天冲天天冲,身体不要啦";
    }
}
