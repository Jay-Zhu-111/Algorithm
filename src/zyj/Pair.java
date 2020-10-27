package zyj;

public class Pair<T, E> {

    public T a;
    public E b;

    public Pair(){}

    public Pair(T a, E b){
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Pair))
            return false;
        return ((Pair<?, ?>) obj).a == this.a && ((Pair<?, ?>) obj).b == this.b;
    }

    @Override
    public int hashCode() {
        return a.hashCode() * 10 + b.hashCode();
    }
}
