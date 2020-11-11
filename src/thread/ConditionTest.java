package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    final Lock lock = new ReentrantLock();
    final Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ConditionTest test = new ConditionTest();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        consumer.start();
        producer.start();
    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        private void consume(){
            try {
                lock.lock();
                System.out.println("wait for signal:  " + Thread.currentThread().getName());
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                System.out.println("get a signal:  " + Thread.currentThread().getName());
                lock.unlock();
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            produce();
        }

        private void produce(){
            try {
                lock.lock();
                System.out.println("get lock:  " + Thread.currentThread().getName());
                condition.signalAll();
                System.out.println("throw a signal:  " + Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }
        }
    }

}
