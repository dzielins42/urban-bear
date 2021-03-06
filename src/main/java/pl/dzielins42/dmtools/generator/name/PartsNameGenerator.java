package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class PartsNameGenerator implements NameGenerator {

    private NameGenerator[] generators;

    public PartsNameGenerator(String[]... arrays) {
        super();
        generators = new NameGenerator[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            generators[i] = new ArrayNameGenerator(arrays[i]);
        }
    }

    public PartsNameGenerator(NameGenerator... generators) {
        super();
        this.generators = generators;
    }

    @Override
    public String generate(GeneratorOptions options) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < generators.length; i++) {
            sb.append(generators[i].generate(options));
        }

        return sb.toString();
    }

    @Override
    public long getPossibilitiesCount() {
        long sum = 1;

        for (int i = 0; i < generators.length; i++) {
            sum *= generators[i].getPossibilitiesCount();
        }

        return sum;
    }

}
