package sample.change.me.customvolley.netcode;

import java.io.Serializable;
import java.util.concurrent.FutureTask;

/**
 * Created by Administrator on 2017/4/28.
 */

public class Volley {
    public static <T extends Serializable,M>void sendRequest(T requestInfo,String url,Class<M> responClass,IJsonListener<M> jsonListener){
        //体现策略模式，不同功能由不同类实现
        IHttpListener httpListener=new JsonDealListener<>(responClass,jsonListener);
        //实例一个任务，把请求参数放进去，里面实现具体逻辑
        HttpTask httpTask =new HttpTask(requestInfo,url,httpListener);
        //把httpTask这个任务放进线程池的队列，由线程池管理。
        ThreadPoolManager.getInstance().excute(new FutureTask<Object>(httpTask,null));
    }
}
