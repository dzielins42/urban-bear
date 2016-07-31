package pl.dzielins42.dmtools.generator;

import pl.dzielins42.dmtools.util.RandomGenerator;

public class GeneratorOptions {
    private RandomGenerator random;

    public GeneratorOptions(RandomGenerator random) {
        super();

        if (random == null) {
            throw new IllegalArgumentException();
        }

        this.random = random;
    }

    public RandomGenerator getRandom() {
        return random;
    }

    public void setRandom(RandomGenerator random) {
        if (random == null) {
            throw new IllegalArgumentException();
        }

        this.random = random;
    }

    public boolean nextBoolean() {
        return random.nextBoolean();
    }

    public double nextDouble() {
        return random.nextDouble();
    }

    public float nextFloat() {
        return random.nextFloat();
    }

    public int nextInt() {
        return random.nextInt();
    }

    public int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public long nextLong() {
        return random.nextLong();
    }

}