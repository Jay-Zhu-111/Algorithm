package thread.threadImpl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;

public class CallableImpl implements Callable<Integer> {

    private static final AtomicInteger i = new AtomicInteger(0);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            Callable<Integer> callable = new CallableImpl();
            FutureTask<Integer> futureTask = new FutureTask<>(callable);
            new Thread(futureTask).start();
            System.out.println(Thread.currentThread().getName() + "----" + futureTask.get());
        }

        System.out.println(Thread.currentThread().getName());
    }


    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return i.getAndAdd(1);
    }
}
