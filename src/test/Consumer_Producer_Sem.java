package test;

import java.util.concurrent.Semaphore;

public class Consumer_Producer_Sem {

    public static void main(String[] args) {
        Consumer_Producer_Sem consumer_producer_sem = new Consumer_Producer_Sem(5);

        new Thread(() -> {
            try {
                consumer_producer_sem.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                consumer_producer_sem.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }


    private int count;
    private final Semaphore mutex;
    private final Semaphore empty;
    private final Semaphore full;

    public Consumer_Producer_Sem(int capacity){
        count = 0;
        mutex = new Semaphore(1);
        empty = new Semaphore(capacity);
        full = new Semaphore(0);
    }

    public void produce() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            empty.acquire();
            mutex.acquire();
            System.out.println("produce" + count++);
            mutex.release();
            full.release();
        }
    }

    public void consume() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            full.acquire();
            mutex.acquire();
            System.out.println("consume" + count--);
            mutex.release();
            empty.release();
        }
    }
}
