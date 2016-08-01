package pl.dzielins42.dmtools.util.data;

public class Tuple<T, V> {

    private T first;
    private V second;

    public Tuple(T first, V second) {
        super();
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public V getSecond() {
        return second;
    }

    public void setSecond(V second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "Tuple [first=" + first + ", second=" + second + "]";
    }

}