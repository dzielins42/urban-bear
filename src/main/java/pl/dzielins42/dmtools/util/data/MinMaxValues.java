package pl.dzielins42.dmtools.util.data;

public class MinMaxValues<T extends Comparable<T>> {
    private T min, max;

    public MinMaxValues(T min, T max) {
        super();

        if (min == null || max == null || min.compareTo(max) > 0) {
            throw new IllegalArgumentException();
        }

        this.min = min;
        this.max = max;
    }

    public T getMin() {
        return min;
    }

    public void setMin(T min) {
        if (min == null || min.compareTo(max) > 0) {
            throw new IllegalArgumentException();
        }

        this.min = min;
    }

    public T getMax() {
        return max;
    }

    public void setMax(T max) {
        if (max == null || min.compareTo(max) > 0) {
            throw new IllegalArgumentException();
        }

        this.max = max;
    }

}