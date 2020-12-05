package thread.threadImpl;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool implements Runnable{

    private int i = 0;

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();

        ThreadPool threadPool = new ThreadPool();

        for (int i = 0; i < 8; i++) {
            pool.submit(threadPool);
        }


        pool.shutdown();
    }

    @Override
    public void run() {
        while(i <= 50){
            System.out.println(Thread.currentThread().getName() + "----" + i++);
        }
    }
}
