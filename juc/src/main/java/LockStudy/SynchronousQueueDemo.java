package LockStudy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * SynchronousQueue同步队列
 *
 * 没有容量，
 * 进去一个元素，必须等待取出来之后，才能再往里面放一个元素！
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue queue=new SynchronousQueue();
            new Thread(()->{
                try {
                    queue.put(1);
                    System.out.println(Thread.currentThread().getName()+"put1");
                    queue.put(2);
                    System.out.println(Thread.currentThread().getName()+"put2");
                    queue.put(3);
                    System.out.println(Thread.currentThread().getName()+"put3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },"T1").start();

        new Thread(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"=>"+queue.take());
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"=>"+queue.take());
                TimeUnit.MILLISECONDS.sleep(3000);
                System.out.println(Thread.currentThread().getName()+"=>"+queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}
