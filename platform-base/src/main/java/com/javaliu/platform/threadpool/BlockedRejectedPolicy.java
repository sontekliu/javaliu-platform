package com.javaliu.platform.threadpool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义的线程池拒绝方式
 * 当任务数较多时(队列已满)，新增的任务时，阻塞。
 * 如果使用JDK默认的线程池，默认使用的是无限制队列，容易出现内存溢出
 * @author javaliu
 * @date   2016.12.28
 */
public class BlockedRejectedPolicy implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(!executor.isShutdown()){
            try{
                executor.getQueue().put(r);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
