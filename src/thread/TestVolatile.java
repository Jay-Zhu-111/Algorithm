package thread;

public class TestVolatile {
    public volatile int i = 0;

    public void increase(){
        i++;
    }

    public static void main(String[] args) {
        TestVolatile test = new TestVolatile();
        for (int k = 0; k < 10; k++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    test.increase();
                }
            }).start();
        }

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.println(threads[i].getName());
        }

        while(Thread.activeCount() > 2){
//            System.out.println(Thread.activeCount());
//
//            System.out.println(Thread.currentThread().getName());
            Thread.yield();
        }
        System.out.println(test.i);

    }
}
