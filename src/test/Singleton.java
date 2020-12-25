package test;

import java.util.RandomAccess;

public class Singleton implements RandomAccess {

    private volatile static Singleton instance = null;

    private Singleton(){

    }

    public synchronized static Singleton getInstance(){
        if(instance == null){
            synchronized (Singleton.class){
                if(instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    public void f(){

    }
}
