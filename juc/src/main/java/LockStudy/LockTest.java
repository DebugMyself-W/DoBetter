package LockStudy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    public static void main(String[] args) {
        SaleTicket2 ticketsaler = new SaleTicket2();
        new Thread(() -> {for (int i = 0; i < 10;i++)ticketsaler.saleTicket();},"A").start();
        new Thread(() -> {for (int i = 0; i < 10;i++) ticketsaler.saleTicket();},"B").start();
        new Thread(() -> {for (int i = 0; i < 10;i++) ticketsaler.saleTicket();},"C").start();
    }





}

class SaleTicket2 {
    private int ticketNumber = 50;

    Lock lock=new ReentrantLock();

    public  void saleTicket() {
        lock.lock();
        try {
            if (ticketNumber > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + ticketNumber-- + "票,还剩下" + ticketNumber);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
}