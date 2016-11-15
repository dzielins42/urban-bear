package pl.dzielins42.dmtools.generator.name;

import java.util.List;

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

        List<NameGenerator> generators = elements.getElements();
        for (NameGenerator ng : generators) {
            sum += ng.getPossibilitiesCount();
        }

        return sum;
    }

    @Override
    public String generate(GeneratorOptions options) {
        return elements.getRandom(options.getRandom()).generate(options);
    }

}
