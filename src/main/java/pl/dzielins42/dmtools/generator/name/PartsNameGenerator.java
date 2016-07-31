package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class PartsNameGenerator implements NameGenerator {

    private String[][] arrays;

    public PartsNameGenerator(String[]... arrays) {
        super();
        this.arrays = arrays;
        // TODO validate
    }

    @Override
    public String generate(GeneratorOptions options) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arrays.length; i++) {
            sb.append(arrays[i][options.nextInt(arrays[i].length)]);
        }

        return sb.toString();
    }

}
