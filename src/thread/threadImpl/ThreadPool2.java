package thread.threadImpl;

import java.util.concurrent.*;

public class ThreadPool2 {

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            Future<Integer> future = pool.submit(() -> {
                int result = 0;
                for (int j = 0; j < 10; j++) {
                    result += 10;
                }
                return result;
            });

            try {
                System.out.println(Thread.currentThread().getName() + "----" + future.get());
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }

        pool.shutdown();
    }
}
