package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class RandomizedNameGenerator implements NameGenerator {

    private NameGenerator[] generators;

    public RandomizedNameGenerator(String[]... arrays) {
        super();
        generators = new NameGenerator[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            generators[i] = new ArrayNameGenerator(arrays[i]);
        }
    }

    public RandomizedNameGenerator(NameGenerator... generators) {
        super();
        this.generators = generators;
    }

    @Override
    public String generate(GeneratorOptions options) {
        int i = options.nextInt(generators.length);

        return generators[i].generate(options);
    }

    @Override
    public long getPossibilitiesCount() {
        long sum = 0;

        for (int i = 0; i < generators.length; i++) {
            sum += generators[i].getPossibilitiesCount();
        }

        return sum;
    }

}
