package genneric;

public class DefaultA extends A {

    public Integer i;

    public DefaultA(){
//        super(1);
        this(2);
        System.out.println(super.aaa);
    }

    public DefaultA(Integer a){
        this.i = a;
    }

    @Override
    public DefaultA testA() {

        return null;
    }

    public static void  staticA(){

    }
}
