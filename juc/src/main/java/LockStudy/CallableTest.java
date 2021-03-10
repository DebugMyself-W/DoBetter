package LockStudy;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallSource source=new CallSource();
        FutureTask futureTask=new FutureTask(source);
        FutureTask futureTask2=new FutureTask(source);
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();
        new Thread(futureTask2,"C").start();

        int result= (int) futureTask.get();
        int result2= (int) futureTask2.get();
        System.out.println(result);
        System.out.println(result2);
    }

}
class CallSource implements Callable{

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"执行call()");
        return 1024;
    }
}