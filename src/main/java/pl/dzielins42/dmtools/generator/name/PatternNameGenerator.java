package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class PatternNameGenerator implements NameGenerator {

    public static final int SPACE = -1;

    private int[][] patterns;
    private String[][] arrays;

    public PatternNameGenerator(int[][] patterns, String[]... arrays) {
        super();
        this.patterns = patterns;
        this.arrays = arrays;
        // TODO validate
    }

    @Override
    public String generate(GeneratorOptions options) {
        // Get random pattern
        int[] pattern = patterns[options.nextInt(patterns.length)];

        StringBuilder sb = new StringBuilder();
        // Iterate over pattern
        for (int i = 0; i < pattern.length; i++) {
            if (pattern[i] == SPACE) {
                sb.append(" ");
            } else {
                sb.append(arrays[pattern[i]][options.nextInt(arrays[pattern[i]].length)]);
            }
        }

        return sb.toString();
    }

    @Override
    public long getPossibilitiesCount() {
        long sum = 0;

        for (int i = 0; i < patterns.length; i++) {
            long subSum = 1;
            for (int j = 0; j < patterns[i].length; j++) {
                if (patterns[i][j] >= 0) {
                    subSum *= arrays[patterns[i][j]].length;
                }
            }
            sum += subSum;
        }

        return sum;
    }

}
