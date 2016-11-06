package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;

public class ProbabilityNameGenerator implements NameGenerator {

    private ProbabilityDistributionTable<NameGenerator> elements;

    public ProbabilityNameGenerator(ProbabilityDistributionTable<NameGenerator> elements) {
        super();

        if (elements == null) {
            throw new IllegalArgumentException();
        }

        this.elements = elements;
    }

    @Override
    public long getPossibilitiesCount() {
        int sum = 0;

        NameGenerator[] generators = elements.getElements();
        for (int i = 0; i < generators.length; i++) {
            sum += generators[i].getPossibilitiesCount();
        }

        return sum;
    }

    @Override
    public String generate(GeneratorOptions options) {
        return elements.getRandom(options.getRandom()).generate(options);
    }

}
