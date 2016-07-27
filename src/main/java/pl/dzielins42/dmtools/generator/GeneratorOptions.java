package pl.dzielins42.dmtools.generator;

import pl.dzielins42.dmtools.util.RandomGenerator;

public class GeneratorOptions {
    private RandomGenerator random;

    public GeneratorOptions(RandomGenerator random) {
        super();
        this.random = random;
    }

    public RandomGenerator getRandom() {
        return random;
    }

    public void setRandom(RandomGenerator random) {
        this.random = random;
    }

}