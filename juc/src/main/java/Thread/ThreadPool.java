package Thread;

import java.util.concurrent.*;

/**
 * new ThreadPoolExecutor(int corePoolSize, // 核心线程池大小
 * int maximumPoolSize, // 最大核心线程池大小
 * long keepAliveTime, // 超时了没有人调用就会释放
 * TimeUnit unit, // 超时单位
 * BlockingQueue<Runnable> workQueue, // 阻塞队列
 * ThreadFactory threadFactory, // 线程工厂：创建线程的，一般 不用动
 * RejectedExecutionHandler handle // 拒绝策略)
 */
public class ThreadPool {

    public static void main(String[] args) {
        // 最大线程到底该如何定义
        // 1、CPU 密集型，几核，就是几，可以保持CPu的效率最高！
        // 2、IO 密集型 > 判断你程序中十分耗IO的线程，
        ExecutorService threadPool = new ThreadPoolExecutor(2, 5, 3, TimeUnit.SECONDS, new LinkedBlockingDeque<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        try {
            // 最大承载：Deque + max // 超过 RejectedExecutionException
            for (int i = 1; i <= 9; i++) {
                // 使用了线程池之后，使用线程池来创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 线程池用完，程序结束，关闭线程池
            threadPool.shutdown();
        }
    }
}
