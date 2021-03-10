package LockStudy;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition实现多线程的精准唤醒
 *  A 执行完调用B，B执行完调用C，C执行完调用A
 */
public class ConditionTest {

    public static void main(String[] args) {
        ConditionSource source = new ConditionSource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                source.printA();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                source.printB();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                source.printC();
            }
        }, "C").start();
    }

}

class ConditionSource {
    private  final Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    Integer num=0;
    void printA() {
        lock.lock();
        try {
            while (num!=0) {
                condition1.await();
            }
            num=1;
            System.out.println(Thread.currentThread().getName() + "AAAAAAAAAA");
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void printB() {
        lock.lock();
        try {
            while (num!=1) {
                condition2.await();
            }
            num=2;
            System.out.println(Thread.currentThread().getName() + "BBBBBBBB");
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void printC() {
        lock.lock();
        try {
            while (num!=2) {
                condition3.await();
            }
            num=0;
            System.out.println(Thread.currentThread().getName() + "CCCCCCCCCC");
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}