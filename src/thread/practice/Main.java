package thread.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private volatile boolean flag = true;
    private final Queue<Integer> queue = new LinkedList<>();
    private static volatile int count = 0;
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Main main = new Main();
        main.startThread();
    }

    public void startThread(){
        this.new Consumer().start();
        this.new Producer().start();
    }

    class Consumer extends Thread{
        private void consume(){
            if(!queue.isEmpty()) {
                lock.lock();
                System.out.println(queue.poll());
                lock.unlock();
            }
        }

        @Override
        public void run(){
            while(flag){
                consume();
            }
        }
    }

    class Producer extends Thread{
        private void produce(){
            lock.lock();
            queue.offer(count++);
            if(count == 100){
                flag = false;
            }
            lock.unlock();
        }

        @Override
        public void run(){
            while(flag){
                produce();
            }
        }
    }
}
