package LockStudy;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 可重入锁
 */
public class ReentrantLock {

    public static void main(String[] args) {
        ReentrantLockSource lockSource=new ReentrantLockSource();
        new Thread(()->{
            lockSource.source();
        },"A").start();

        new Thread(()->{
            lockSource.source();
        },"B").start();
    }


}
class ReentrantLockSource{
    Lock lock=new java.util.concurrent.locks.ReentrantLock();
    Lock lock2=new java.util.concurrent.locks.ReentrantLock();

    void source(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"拿到了source锁");
            if("A".equals(Thread.currentThread().getName())) TimeUnit.SECONDS.sleep(3);
            source2();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void source2(){
        lock2.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"拿到了source2锁");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock2.unlock();
        }
    }
}
