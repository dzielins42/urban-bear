package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class RepetitiveNameGenerator implements NameGenerator {

    private NameGenerator generator;
    int minReps, maxReps;

    public RepetitiveNameGenerator(NameGenerator generator, int minReps, int maxReps) {
        super();

        if (generator == null || minReps <= 0 || maxReps < minReps) {
            throw new IllegalArgumentException();
        }

        this.generator = generator;
        this.minReps = minReps;
        this.maxReps = maxReps;
    }

    @Override
    public long getPossibilitiesCount() {
        int sum = 0;

        for (int i = minReps; i <= maxReps; i++) {
            sum += Math.pow(generator.getPossibilitiesCount(), i);
        }

        return sum;
    }

    @Override
    public String generate(GeneratorOptions options) {
        int c = minReps + options.nextInt(maxReps - minReps + 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < c; i++) {
            sb.append(generator.generate(options));
        }

        return sb.toString();
    }

}
