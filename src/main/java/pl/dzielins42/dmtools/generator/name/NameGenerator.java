package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.model.Gender;

public interface NameGenerator {

    String generate(Gender gender, GeneratorOptions options);

}