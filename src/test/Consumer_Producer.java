package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer_Producer<T> {

    public static void main(String[] args) throws InterruptedException {
        Consumer_Producer<Integer> consumer_producer = new Consumer_Producer<>(2, 1, 10);
        consumer_producer.startThread();
        Thread.sleep(2000);
        consumer_producer.setT(66666666);
        Thread.sleep(2000);
        consumer_producer.interrupt();
        System.out.println("end");
    }

    private volatile boolean flag = true;
    private final Queue<T> queue = new LinkedList<>();
    private static final int maxSize = 5;
    private volatile T t;
    private int numOfConsumer = 1;
    private int numOfProducer = 1;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition not_full = lock.newCondition();
    private final Condition not_empty = lock.newCondition();
    private final List<Thread> threadList = new LinkedList<>();

    public Consumer_Producer(T t){
        this.t = t;
    }

    public Consumer_Producer(T t, int numOfConsumer, int numOfProducer){
        this.t = t;
        this.numOfConsumer = numOfConsumer;
        this.numOfProducer = numOfProducer;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void interrupt(){
        flag = false;
        for (Thread thread : threadList) {
            thread.interrupt();
        }
    }

    private void startThread(){
        for (int i = 0; i < numOfConsumer; i++) {
            threadList.add(new Thread(new Consumer()));
        }

        for (int i = 0; i < numOfProducer; i++) {
            threadList.add(new Thread(new Producer()));
        }

        for(Thread thread : threadList){
            thread.start();
        }
    }


    private class Consumer implements Runnable{
        @Override
        public void run() {
            while(flag){
                consume();
            }
        }

        public void consume(){
            try {
                lock.lock();
                while(queue.isEmpty()){
                    System.out.println("start wait");
                    not_empty.await();
                }
                System.out.println(Thread.currentThread().getName() + " consume:  " + queue.poll().toString());
                not_full.signal();
            } catch (InterruptedException e){
                flag = false;
            } finally {
                lock.unlock();
            }
        }
    }

    private class Producer implements Runnable{
        @Override
        public void run() {
            while(flag){
                produce();
            }
        }

        public void produce(){
            try {
                lock.lock();
                while(queue.size() == maxSize){
                    not_full.await();
                }
                queue.offer(t);
                System.out.println(Thread.currentThread().getName() + "  produce:  " + t.toString());
                not_empty.signal();
            } catch (InterruptedException e) {
                flag = false;
            } finally {
                lock.unlock();
            }
        }
    }
}
