package pl.dzielins42.dmtools.generator.name;

import java.util.EnumMap;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.model.Gender;

/**
 * {@link NameGenerator} implementation which uses arrays for each
 * {@link Gender} and draws random name from corresponding array.
 */
public class ArrayNameGenerator implements NameGenerator {

    private EnumMap<Gender, String[]> arrays;

    public ArrayNameGenerator(String[] male, String[] female, String[] other) {
        super();
        this.arrays = new EnumMap<>(Gender.class);
        this.arrays.put(Gender.MALE, male);
        this.arrays.put(Gender.FEMALE, female);
        this.arrays.put(Gender.OTHER, other);
    }

    public ArrayNameGenerator(EnumMap<Gender, String[]> map) {
        super();
        this.arrays = map;
    }
    
    public EnumMap<Gender, String[]> get(){return arrays;}

    @Override
    public String generate(Gender gender, GeneratorOptions options) {
        String[] array = arrays.get(gender);

        if (array == null || array.length <= 0) {
            return null;
        }

        return array[options.getRandom().nextInt(array.length)];
    }

}
