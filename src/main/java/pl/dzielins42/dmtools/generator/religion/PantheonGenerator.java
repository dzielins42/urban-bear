package pl.dzielins42.dmtools.generator.religion;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.model.religion.Pantheon;

public interface PantheonGenerator<T extends GeneratorOptions> {

    Pantheon generate(T options);

}