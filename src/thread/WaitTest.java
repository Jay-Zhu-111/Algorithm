package thread;

public class WaitTest {

    static int count = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (WaitTest.class){
                while(count <= 100){
                    try {
                        System.out.println(count++);
                        WaitTest.class.notify();
                        WaitTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (WaitTest.class){
                while(count <= 100){
                    try {
                        System.out.println(count++);
                        WaitTest.class.notify();
                        WaitTest.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
