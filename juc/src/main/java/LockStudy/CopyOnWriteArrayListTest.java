package LockStudy;

import java.lang.reflect.Array;
import java.util.*;

/**
 *
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        //ArrayList并发下是不安全的
        List<String> list1 = new Vector<>();
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());
        List<String> list3 = new java.util.concurrent.CopyOnWriteArrayList<>();
        List<String> list4 = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            new Thread(() -> {
                list4.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list4);
            }, String.valueOf(i)).start();
        }
    }
}