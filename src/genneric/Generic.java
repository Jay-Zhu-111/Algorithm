package genneric;

public class Generic<T> {

    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public <E extends A & Generator<?>> int test(E a, T b){
        a.method();
        a.testA();
        this.value = b;
        return 0;
    }
}
