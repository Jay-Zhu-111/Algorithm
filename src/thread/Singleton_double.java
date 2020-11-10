package thread;

public class Singleton_double {

    private volatile static Singleton_double instance = null;

    private Singleton_double(){

    }

    public static Singleton_double getInstance(){
        //第一次实例化后，每次getInstance不再需要获取锁
        if(instance == null){
            //可能有多个线程进入此处，再次判断是否为空
            synchronized (Singleton_double.class){
                if(instance == null){
                    instance = new Singleton_double();
                }
            }
        }
        return instance;
    }
}
