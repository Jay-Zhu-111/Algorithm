package thread;

import java.util.concurrent.locks.ReentrantLock;

public class WaitTest2 {

    static ReentrantLock lock = new ReentrantLock(true);
    static int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while(count < 100){
                lock.lock();
                System.out.println(count++);
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            while(count < 100){
                lock.lock();
                System.out.println(count++);
                lock.unlock();
            }
        }).start();
    }
}
