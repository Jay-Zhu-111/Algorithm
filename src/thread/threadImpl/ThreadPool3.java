package thread.threadImpl;

import connection.Server;

import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool3 {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println(pool);

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            pool.execute(() -> System.out.println(Thread.currentThread().getName() + ", " + finalI));
        }
        System.out.println(Thread.currentThread().getName() + ",  " + pool);
        Thread.sleep(5000);
        pool.shutdown();
    }
}
