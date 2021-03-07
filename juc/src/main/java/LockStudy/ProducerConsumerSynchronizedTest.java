package LockStudy;

public class ProducerConsumerSynchronizedTest {

    public static void main(String[] args) {
        NumberSource numberSource=new NumberSource();
        new Thread(()->{ for (int i = 0; i <10;i++ ) numberSource.numberAdd();},"A").start();
        new Thread(()->{for (int i = 0; i <10;i++ ) numberSource.numberSubstract();},"B").start();
        new Thread(()->{for (int i = 0; i <10;i++ ) numberSource.numberAdd();},"C").start();
        new Thread(()->{for (int i = 0; i <10;i++ ) numberSource.numberSubstract();},"D").start();

    }


}

class NumberSource {
    private int number=0;
    public synchronized void numberAdd() {
        while (number!=0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"->"+number);
        notifyAll();
    }

    public synchronized void numberSubstract()  {
        //while 解决虚假唤醒问题
        while (number != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"->"+number);
        notifyAll();
    }


}
