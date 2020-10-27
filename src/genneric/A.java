package genneric;

public class A extends SuperA{

    protected Integer aaa;

    public A(){
        System.out.println("A none");
    }

    public A(Integer a){
        System.out.println(a);
    }

    public static void staticA(){
        System.out.println("AAA");
    }

    protected A testA(){
        System.out.println("A1");
        return null;
    }
}
