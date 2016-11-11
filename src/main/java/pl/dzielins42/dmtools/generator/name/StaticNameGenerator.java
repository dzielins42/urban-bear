package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class StaticNameGenerator implements NameGenerator {
    
    private final String value;

    public StaticNameGenerator(String value) {
        super();
        this.value = value;
    }

    @Override
    public long getPossibilitiesCount() {
        return 1;
    }

    @Override
    public String generate(GeneratorOptions options) {
        return value;
    }

}
