package com.javaliu.platform.threadpool;

import com.javaliu.platform.Global;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池
 * @author javaliu
 *
 */
public class ThreadPool {

	private static ThreadPool INSTANCE = null;
	private static ThreadPoolExecutor executor = null;
	public static final ReentrantLock LOCK = new ReentrantLock();
	private ThreadPool(){
		if(null != INSTANCE){
			throw new RuntimeException("线程池实例以存在，请勿重复创建");
		}
	}
	static{
		int minSize = Global.DEFAULT_THREAD_CORE_SIZE;
		int maxSize = Global.DEFAULT_THREAD_MAX_SIZE;
		int keepTime = Global.DEFAULT_THREAD_KEEP_TIME;
		int queueSize = Global.DEFAULT_THREAD_QUEUE_SIZE;

		executor = new ThreadPoolExecutor(minSize, maxSize, keepTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize),
                new BlockedRejectedPolicy());
		executor.allowCoreThreadTimeOut(true);
	}
	
	public static class ThreadPoolHolder{
		private static final ThreadPool pool = new ThreadPool();
	}
	
	/**
	 * 获取线程池实例
	 * @return
	 * @author shijun.liu
	 */
	public static ThreadPool getInstance(){
		INSTANCE = ThreadPoolHolder.pool;
		return INSTANCE;
	}
	
	/**
	 * 获取线程池对象信息
	 * @return
	 * @author shijun.liu
	 */
	public ThreadPoolExecutor getThreadExecutor(){
		if(null != executor){
			return executor;
		}
		return null;
	}
	
	/**
	 * 启动一个线程去执行任务
	 * @param task
	 * @return
	 */
	public Runnable runTask(Runnable task){
		if(null == task){
			throw new NullPointerException(task + "task is null ");
		}
		if(null != executor){
			LOCK.lock();
			try {
				executor.execute(task);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				LOCK.unlock();
			}
		}
		return null;
	}

	/**
	 * 设置线程池大小
	 * @param size	线程池的大小
	 * @return
	 * @author	liushijun
     */
	public ThreadPoolExecutor setCorePoolSize(int size){
		if(null != executor){
			executor.setCorePoolSize(size);
		}
		return executor;
	}

	/**
	 * 设置线程池最大值
	 * @param size		线程池线程的最大值
	 * @return
	 * @author	liushijun
     */
	public ThreadPoolExecutor setMaxPoolSize(int size){
		if(null != executor){
			executor.setMaximumPoolSize(size);
		}
		return executor;
	}

	/**
	 * 设置空闲线程在线程池存在的时间
	 * @param seconds		时间，单位秒
     * @return
     */
	public ThreadPoolExecutor setKeepAliveTime(long seconds){
		if(null != executor){
			executor.setKeepAliveTime(seconds, TimeUnit.SECONDS);
		}
		return executor;
	}
	
}
