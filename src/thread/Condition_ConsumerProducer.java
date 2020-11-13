package thread;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Condition_ConsumerProducer {
    private final int queueSize = 10;
    private final PriorityQueue<Integer> queue = new PriorityQueue<>();
    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Condition_ConsumerProducer test = new Condition_ConsumerProducer();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
        producer.start();
        consumer.start();
        Thread.sleep(1000);
        producer.interrupt();
        consumer.interrupt();
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        volatile boolean flag = true;

        private void consume(){
            while(flag){
                lock.lock();
                try {
                    while(queue.isEmpty()){
                        try {
                            System.out.println("queue is empty, waiting for data");
                            notEmpty.await();
                        }catch (InterruptedException e){
                            flag = false;
                        }
                    }
                    System.out.println(queue);
                    queue.poll();
                    notFull.signal();
                    System.out.println("get one data, size " + queue.size());
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        volatile boolean flag = true;
        volatile AtomicInteger num = new AtomicInteger(0);

        private void produce(){
            while(flag){
                lock.lock();
                System.out.println("producer get into lock");
                try {
                    while(queue.size() == queueSize){
                        System.out.println(queue);
                        try {
                            System.out.println("queue is full, waiting for place");
                            notFull.await();
                        }catch (InterruptedException e){
                            flag = false;
                        }
                    }

                    queue.offer(num.get());
                    System.out.println("insert  " + num + ", size " + queue.size());
                    num.getAndAdd(1);
                    notEmpty.signal();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
