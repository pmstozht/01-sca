package cn.cd.zlit.common.filter;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @description:
 * @author: _zlit
 * @create: 2021-10-29 09:45
 **/

//过滤器(特殊拦截器)
interface Filter{
    boolean invoke();
}
//控制器(负责请求分发)
interface Servlet{
    void dispath();
}

class FileChain{
    private List<Filter> filters = new CopyOnWriteArrayList<>();//过滤器(请求数据过滤)
    private Servlet servlet;//控制器(请求控制逻辑)
    public FileChain(List<Filter> filters, Servlet servlet) {
        this.filters.addAll(filters);
        this.servlet = servlet;
    }
    public void doFilter(){//执行过滤链
        for (int i = 0; i < filters.size(); i++) {
             if (!filters.get(i).invoke()) return;
        }
        servlet.dispath();//请求分析
    }
}


public class FileChainTests {
    public static void main(String[] args) {
        //1.构建过滤器对象
        Filter filter = new Filter() {
            @Override
            public boolean invoke() {
                System.out.println("filter");
                return true;
            }
        };
        Filter filter2 = new Filter() {
            @Override
            public boolean invoke() {
                System.out.println("filter2");
                return true;
            }
        };
        CopyOnWriteArrayList<Filter> list = new CopyOnWriteArrayList<>();
        list.add(filter);
        list.add(filter2);
        Servlet servlet = new Servlet() {
            @Override
            public void dispath() {
                System.out.println("具体分发请求取执行");
            }
        };
        FileChain fc = new FileChain(list,servlet);
        fc.doFilter();
    }
}
