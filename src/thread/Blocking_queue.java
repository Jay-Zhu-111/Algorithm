package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Blocking_queue {

    private final Lock lock = new ReentrantLock();
    private final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        Blocking_queue queue = new Blocking_queue();
        testA testA = queue.new testA();
        testB testB = queue.new testB();
        testA.start();
        testB.start();
    }

    class testA extends Thread{
        private void function() throws InterruptedException {
            lock.lock();
            for (int i = 0; i < 3; i++) {
                System.out.println("a");
            }
            Thread.sleep(5000);
            for (int i = 0; i < 3; i++) {
                System.out.println("a");
            }
            lock.unlock();
        }

        @Override
        public void run() {
            try {
                function();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class testB extends Thread {
        private void function() {
            lock2.lock();
            System.out.println("b");
            lock2.unlock();
        }

        @Override
        public void run() {
            function();
        }
    }
}
