package pl.dzielins42.dmtools.generator.name;

import java.util.EnumMap;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.model.Gender;

public class CharacterNameGenerator {

    private EnumMap<Gender, NameGenerator> generators;

    public CharacterNameGenerator(String[] male, String[] female, String[] other) {
        super();
        this.generators = new EnumMap<Gender, NameGenerator>(Gender.class);
        if (male != null && male.length > 0) {
            this.generators.put(Gender.MALE, new ArrayNameGenerator(male));
        }
        if (female != null && female.length > 0) {
            this.generators.put(Gender.FEMALE, new ArrayNameGenerator(female));
        }
        if (other != null && other.length > 0) {
            this.generators.put(Gender.OTHER, new ArrayNameGenerator(other));
        }
    }

    public CharacterNameGenerator(NameGenerator male, NameGenerator female, NameGenerator other) {
        super();
        this.generators = new EnumMap<Gender, NameGenerator>(Gender.class);
        this.generators.put(Gender.MALE, male);
        this.generators.put(Gender.FEMALE, female);
        this.generators.put(Gender.OTHER, other);
    }

    public String generate(Gender param, GeneratorOptions options) {
        NameGenerator generator = generators.get(param);

        if (generator == null) {
            return null;
        }

        return generator.generate(options);
    }

}