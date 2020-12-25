package thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {

    private static final Lock LOCK = new ReentrantLock(true);

    public static void main(String[] args) {
        new Thread(ReentrantLockTest::test, "A").start();
        new Thread(ReentrantLockTest::test, "B").start();
        new Thread(ReentrantLockTest::test, "C").start();
        new Thread(ReentrantLockTest::test, "D").start();
        new Thread(ReentrantLockTest::test, "E").start();
    }

    private static void test(){
        for (int i = 0; i < 5; i++) {
            try {
                LOCK.lock();
                System.out.println(Thread.currentThread().getName() + "  get LOCK");
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
//                System.out.println(Thread.currentThread().getName() + "  free LOCK");
                LOCK.unlock();
            }
        }
    }
}
