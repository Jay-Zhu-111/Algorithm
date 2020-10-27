package zyj;

public class Singleton {

    //禁止创建对象
    private Singleton(){

    }

    private static class SingletonHolder{
        public static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getUniqueInstance(){
        return SingletonHolder.INSTANCE;
    }
}
