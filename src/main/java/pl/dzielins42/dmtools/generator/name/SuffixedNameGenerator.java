package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class SuffixedNameGenerator extends StaticNameGenerator {

    private NameGenerator generator;

    public SuffixedNameGenerator(String value, NameGenerator generator) {
        super(value);

        if (generator == null) {
            throw new IllegalArgumentException();
        }
        this.generator = generator;
    }

    @Override
    public long getPossibilitiesCount() {
        return generator.getPossibilitiesCount();
    }

    @Override
    public String generate(GeneratorOptions options) {
        return generator.generate(options) + super.generate(options);
    }

}
