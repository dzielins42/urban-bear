package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.generator.GeneratorStatistics;

public interface NameGenerator extends GeneratorStatistics {

    String generate(GeneratorOptions options);

}