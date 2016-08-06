package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.model.Gender;

/**
 * {@link NameGenerator} implementation which uses arrays for each
 * {@link Gender} and draws random name from corresponding array.
 */
public class ArrayNameGenerator implements NameGenerator {

    private String[] array;

    public ArrayNameGenerator(String[] array) {
        super();

        if (array == null || array.length <= 0) {
            throw new IllegalArgumentException();
        }

        this.array = array;
    }

    @Override
    public String generate(GeneratorOptions options) {
        if (array == null || array.length <= 0) {
            return null;
        }

        return array[options.getRandom().nextInt(array.length)];
    }

    @Override
    public long getPossibilitiesCount() {
        return array.length;
    }

}
