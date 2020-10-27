package genneric;

public interface Generator<T> {
    public default int method() {
        return 0;
    }
}
