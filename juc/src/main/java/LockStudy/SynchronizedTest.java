package LockStudy;

public class SynchronizedTest {

    public static void main(String[] args) {
        SaleTicket ticketsaler = new SaleTicket();
        new Thread(() -> {
            for (int i = 0; i < 10;i++) {
                ticketsaler.saleTicket();
            }
        },"A").start();
        new Thread(() -> {
            for (int i = 0; i < 10;i++) {
                ticketsaler.saleTicket();
            }
        },"B").start();
        new Thread(() -> {
            for (int i = 0; i < 10;i++) {
                ticketsaler.saleTicket();
            }
        },"B").start();

    }





}
class SaleTicket {
    private int ticketNumber = 50;

    public synchronized void saleTicket() {
        if (ticketNumber > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了第" + ticketNumber-- + "票,还剩下" + ticketNumber);
        }
    }
}