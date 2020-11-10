package thread;

public class Singleton_inner {

    private Singleton_inner(){

    }

    public static class Holder{
        static Singleton_inner instance = new Singleton_inner();
    }

    public static Singleton_inner getInstance(){
        return Holder.instance;
    }
}
