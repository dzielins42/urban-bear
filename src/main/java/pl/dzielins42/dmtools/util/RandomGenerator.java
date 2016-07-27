package pl.dzielins42.dmtools.util;

public interface RandomGenerator {

    boolean nextBoolean();

    double nextDouble();

    float nextFloat();

    int nextInt();

    int nextInt(int bound);

    long nextLong();

}