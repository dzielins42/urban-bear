package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class PrefixedNameGenerator extends StaticNameGenerator {

    private NameGenerator generator;

    public PrefixedNameGenerator(String value, NameGenerator generator) {
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
        return super.generate(options) + generator.generate(options);
    }

}
