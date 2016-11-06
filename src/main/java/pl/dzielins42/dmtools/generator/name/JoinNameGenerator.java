package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class JoinNameGenerator implements NameGenerator {

    private NameGenerator first, second;
    private String separator;

    public JoinNameGenerator(NameGenerator first, NameGenerator second, String separator) {
        super();

        if (first == null || second == null) {
            throw new IllegalArgumentException();
        }

        this.first = first;
        this.second = second;
        this.separator = separator;
    }

    @Override
    public long getPossibilitiesCount() {
        return first.getPossibilitiesCount() * second.getPossibilitiesCount();
    }

    @Override
    public String generate(GeneratorOptions options) {
        StringBuilder sb = new StringBuilder();

        sb.append(first.generate(options)).append(separator).append(second.generate(options));

        return sb.toString();
    }

}
