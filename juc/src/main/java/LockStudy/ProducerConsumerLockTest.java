package LockStudy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumerLockTest {

    public static void main(String[] args) {
        NumberSource2 numberSource = new NumberSource2();
        new Thread(() -> { for (int i = 0; i < 10; i++) numberSource.numberAdd(); }, "A").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) numberSource.numberSubstract(); }, "B").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) numberSource.numberAdd(); }, "C").start();
        new Thread(() -> { for (int i = 0; i < 10; i++) numberSource.numberSubstract(); }, "D").start();
    }


}

class NumberSource2 {
    private int number = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    public void numberAdd() {
        lock.lock();
        try {
            while (number != 0) {
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }

    public  void numberSubstract() {

        lock.lock();
        try {
            while (number != 1) {
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName() + "->" + number);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }




}


