package LockStudy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读锁与读锁不互斥；读锁与写锁，写锁与写锁互斥
 * 独占锁（写锁） 一次只能被一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        ReadWriteSource2 source=new ReadWriteSource2();

        for (int i = 1; i <=5 ; i++) {
            int temp=i;
            new Thread(()->{
                source.putAction(temp,temp+"value");
            },String.valueOf(temp)).start();
        }

        for (int i = 1; i <=5 ; i++) {
            int temp=i;
            new Thread(()->{
                source.getAction(temp);
            },String.valueOf(temp)).start();
        }
    }
}

class ReadWriteSource2{

    private volatile Map<Integer,String> map=new HashMap<>();
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    public void putAction(Integer key,String value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"开始写入");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public String getAction(Integer key){
        readWriteLock.readLock().lock();
        String value= null;
        try {
            System.out.println(Thread.currentThread().getName()+"开始读取");
            value = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取完毕"+key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return  value;
    }
}


class ReadWriteSource{

    private volatile Map<Integer,String> map=new HashMap<>();
    public void putAction(Integer key,String value){
        System.out.println(Thread.currentThread().getName()+"开始写入");
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入完毕");
    }

    public String getAction(Integer key){
        System.out.println(Thread.currentThread().getName()+"开始读取");
        String value=map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取完毕"+key);
        return  value;
    }
}
