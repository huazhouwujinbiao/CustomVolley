package sample.change.me.customvolley.netcode;

import android.util.Log;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/4/29.
 */

public class ThreadPoolManager {
    //单例模式
    private static ThreadPoolManager instance =new ThreadPoolManager();
    //线程池
    private ThreadPoolExecutor threadPoolExecutor;
    //请求队列
    private LinkedBlockingQueue<Future<?>> queue=new LinkedBlockingQueue<>();
    public static ThreadPoolManager getInstance() {
        return instance;
    }

    private ThreadPoolManager(){
        //int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler)
        threadPoolExecutor=new ThreadPoolExecutor(
                4,      //核心线程数量
                10,     //最大线程数量
                10,     //持续等待链接时间
                TimeUnit.SECONDS,//秒
                new ArrayBlockingQueue<Runnable>(4,false),//如果当前线程池达到核心线程数时，且当前所有线程都处于活动状态时，将新加入的任务加入到此队列中
                handler);   //拒绝策略，当线程池与上面这个队列都满了的情况下，对新加入的任务采取的处理策略
        threadPoolExecutor.execute(runnable);
    }

    /**
     * 拒绝处理机制
     */
    RejectedExecutionHandler handler=new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            try {
                Log.d("log", "rejectedExecution: ----" +threadPoolExecutor.getTaskCount());
                queue.put(new FutureTask<Object>(runnable,null));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            while (true){
                FutureTask task=null;
                try {
                    Log.d("log", "run: Queue size-------"+ queue.size());
                    //take()方法是一个阻塞式函数
                    task= (FutureTask) queue.take();
                    Log.d("log", "run: 线程池的数量 size------------"+ threadPoolExecutor.getPoolSize());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //拿到task之后丢到线程池里面
                if(task!=null){
                    threadPoolExecutor.execute(task);
                }
            }
        }
    };
    public <T>void excute(FutureTask<T> futureTask){
        if(futureTask!=null){
            try {
                //丢在队列中，让队列管理
                queue.put(futureTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
