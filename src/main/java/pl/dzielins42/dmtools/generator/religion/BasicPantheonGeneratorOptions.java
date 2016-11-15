package pl.dzielins42.dmtools.generator.religion;

import java.util.List;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.generator.name.CharacterNameGenerator;
import pl.dzielins42.dmtools.generator.name.NameGenerator;
import pl.dzielins42.dmtools.model.religion.Domain;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;
import pl.dzielins42.dmtools.util.RandomGenerator;

public class BasicPantheonGeneratorOptions extends GeneratorOptions {

    private int minDeitiesNumber;
    private int maxDeitiesNumber;
    private ProbabilityDistributionTable<Domain> domainsProbability;
    private CharacterNameGenerator nameGenerator;

    public BasicPantheonGeneratorOptions(RandomGenerator random, ProbabilityDistributionTable<Domain> domainsProbability,
            CharacterNameGenerator nameGenerator) {
        this(random, 1, Byte.MAX_VALUE, domainsProbability, nameGenerator);
    }

    public BasicPantheonGeneratorOptions(RandomGenerator random, int minDeitiesNumber, int maxDeitiesNumber,
            ProbabilityDistributionTable<Domain> domainsProbability, CharacterNameGenerator nameGenerator) {
        super(random);

        if (minDeitiesNumber < 0 || maxDeitiesNumber < 0 || minDeitiesNumber > maxDeitiesNumber || domainsProbability == null
                || nameGenerator == null) {
            throw new IllegalArgumentException();
        }

        this.maxDeitiesNumber = maxDeitiesNumber;
        this.minDeitiesNumber = minDeitiesNumber;
        this.domainsProbability = domainsProbability;
        this.nameGenerator = nameGenerator;
    }

    public int getMaxDeitiesNumber() {
        return maxDeitiesNumber;
    }

    public void setMaxDeitiesNumber(int maxDeitiesNumber) {
        this.maxDeitiesNumber = maxDeitiesNumber;
    }

    public int getMinDeitiesNumber() {
        return minDeitiesNumber;
    }

    public void setMinDeitiesNumber(int minDeitiesNumber) {
        this.minDeitiesNumber = minDeitiesNumber;
    }

    public ProbabilityDistributionTable<Domain> getDomainsProbability() {
        return domainsProbability;
    }

    public void setDomainsProbability(ProbabilityDistributionTable<Domain> domainsProbability) {
        this.domainsProbability = domainsProbability;
    }

    public List<Domain> getDomains() {
        return getDomainsProbability().getElements();
    }

    public CharacterNameGenerator getNameGenerator() {
        return nameGenerator;
    }

    public void setNameGenerator(CharacterNameGenerator nameGenerator) {
        this.nameGenerator = nameGenerator;
    }

}