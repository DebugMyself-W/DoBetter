package LockStudy;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinLock {

    AtomicReference atomicReference = new AtomicReference();

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==>持有锁");
        while (!atomicReference.compareAndSet(null, thread)) {
        };
    }

    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> 解锁");
        atomicReference.compareAndSet(thread, null);
    }
}

class SpinLockTest {
    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        new Thread(() -> {
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }
        }, "T1").start();

        //为了使T1先获取锁
        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            lock.myLock();
            try {

            } finally {
                lock.myUnLock();
            }
        }, "T2").start();
    }
}
