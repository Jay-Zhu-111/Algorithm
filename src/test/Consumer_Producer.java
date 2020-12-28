package test;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class Consumer_Producer<T> {

    public static void main(String[] args) throws InterruptedException {
        Consumer_Producer<Integer> consumer_producer = new Consumer_Producer<>(2, 1, 10);
        consumer_producer.startThread1();
        Thread.sleep(2000);
        consumer_producer.setT(66666666);
        Thread.sleep(2000);
        consumer_producer.interrupt();
        System.out.println("end");
    }

    private volatile boolean flag = true;
    private final Queue<T> queue = new LinkedList<>();
    private static final int maxSize = 10;
    private volatile T t;
    private int numOfConsumer = 1;
    private int numOfProducer = 1;
    private final List<Thread> list = new LinkedList<>();


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
        for (Thread thread : list) {
            thread.interrupt();
        }
    }

    private void startThread(){
        for (int i = 0; i < numOfProducer; i++) {
            list.add(new Thread(new Producer()));
        }

        for (int i = 0; i < numOfConsumer; i++) {
            list.add(new Thread(new Consumer()));
        }

        for (Thread thread : list) {
            System.out.println(thread.getName() + "  start");
            thread.start();
        }
        System.out.println("start all");
    }

    private void startThread1(){
        System.out.println("numOfProducer  :" + numOfProducer);
        for (int i = 0; i < numOfProducer; i++) {
            new Thread(new Producer()).start();
        }

        System.out.println("numOfConsumer  :" + numOfConsumer);
        for (int i = 0; i < numOfConsumer; i++) {
            new Thread(new Consumer()).start();
        }
    }

    private void startThread2(){
        new Thread(new Producer()).start();
//        new Thread(new Producer()).start();
//        new Thread(new Producer()).start();
//        new Thread(new Producer()).start();
//        new Thread(new Producer()).start();
//        new Thread(new Producer()).start();

        new Thread(new Consumer()).start();
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
                synchronized (Consumer_Producer.this){
                    while(queue.isEmpty()){
                        System.out.println("start wait");
                        Consumer_Producer.this.wait();
                    }
                    System.out.println(queue.poll().toString());
                    Consumer_Producer.this.notify();
                }
            } catch (InterruptedException e){
                flag = false;
            }
        }
    }

    private class Producer implements Runnable{
        @Override
        public void run() {
            while(flag){
                produce();
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        }

        public void produce(){
            try {
                synchronized (Consumer_Producer.this){
                    while(queue.size() == maxSize){
                        Consumer_Producer.this.wait();
                    }
                    queue.offer(t);
//                    System.out.println("size:  " + queue.size());
                    System.out.println("num:   " + t.toString());
                    Consumer_Producer.this.notify();
                }
            } catch (InterruptedException e) {
                flag = false;
            }
        }
    }
}
