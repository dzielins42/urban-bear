package pl.dzielins42.dmtools.generator.religion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.dzielins42.dmtools.model.enumeration.Alignment;
import pl.dzielins42.dmtools.model.enumeration.Gender;
import pl.dzielins42.dmtools.model.religion.Deity;
import pl.dzielins42.dmtools.model.religion.Domain;
import pl.dzielins42.dmtools.model.religion.Pantheon;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;

public class BasicPantheonGenerator implements PantheonGenerator<BasicPantheonGeneratorOptions> {

    protected final int MAX_DIVINE_RANK = 25;

    public Pantheon generate(BasicPantheonGeneratorOptions options) {
        // Validate options
        if (options == null || options.getRandom() == null || options.getDomainsProbability() == null
                || options.getNameGenerator() == null) {
            throw new IllegalArgumentException();
        }

        // Generate number of deities as random number between minDeitiesNumber
        // and maxDeitiesNumber
        int numberOfDeities = options.getMinDeitiesNumber();
        if (options.getMinDeitiesNumber() != options.getMaxDeitiesNumber()) {
            numberOfDeities += options.getRandom().nextInt(options.getMaxDeitiesNumber() - options.getMinDeitiesNumber() + 1);
        }

        // Generate each deity independently
        List<Deity> deities = new ArrayList<Deity>(numberOfDeities);
        Deity deity;
        for (int i = 0; i < numberOfDeities; i++) {
            deity = generateDeity(options);
            deities.add(deity);
        }

        return new Pantheon("The Pantheon", deities);
    }

    protected Deity generateDeity(BasicPantheonGeneratorOptions options) {
        // Generate rank
        // TODO higher ranks should be rarer, probably by some mathematical
        // formula
        // Basic pantheons should have a few greater deities (16-20) but mostly
        // intermediate deities (11-15) and lesser deities (6-10), demigods
        // (1-5) and heroes (0) if the pantheon size enables it. There should
        // not be many overdeities (21+).
        int rank = options.getRandom().nextInt(MAX_DIVINE_RANK + 1);
        // Generate domains
        // Number of deity's domains is its ceiling of its rank divided by 5
        int numberOfDomains = (int) Math.ceil(((double) rank) / 5.0d);
        // Temporarily it is 3
        numberOfDomains = 3;
        // If it is overdeity, its power is beyond domain partitioning - it has
        // power over every domain
        List<Domain> domains = new ArrayList<Domain>();
        Domain domain;
        while (domains.size() < numberOfDomains) {
            domain = options.getDomainsProbability().getRandom(options.getRandom());
            if (!domains.contains(domain)) {
                domains.add(domain);
            }
        }
        Alignment alignment = getRandomAlignmentForDomains(domains, options);
        Gender gender = Gender.values()[options.getRandom().nextInt(Gender.values().length)];
        Deity deity = new Deity(options.getNameGenerator().generate(gender, options), alignment, gender, rank, domains);

        return deity;
    }

    /**
     * Returns deity's {@link Domain} list suited for pre-drawn
     * {@link Alignment}.
     * 
     * @param alignment
     *            deity's alignment.
     * @param options
     *            generation options.
     * @return deity's {@link Domain} list suited for pre-drawn
     *         {@link Alignment}.
     */
    protected Domain getRandomDomainForAlignment(Alignment alignment, BasicPantheonGeneratorOptions options) {
        return null;
    }

    /**
     * Returns deity's {@link Alignment} suited for pre-drawn {@link Domain}
     * list. For each domain, probability for each alignment is retrieved using
     * {@link Domain#getAlignmentProbabilities()} method. Values are used to
     * create new {@link ProbabilityDistributionTable}, which is used to get
     * returned alignment.
     * 
     * @param domains
     *            list of domains of the deity.
     * @param options
     *            generation options.
     * @return deity's {@link Alignment} suited for pre-drawn {@link Domain}
     *         list.
     */
    protected Alignment getRandomAlignmentForDomains(List<Domain> domains, BasicPantheonGeneratorOptions options) {
        // TODO maybe return random alignment based on uniform distribution
        if (domains == null || domains.isEmpty()) {
            throw new IllegalArgumentException();
        }

        double[] probabilities = new double[Alignment.values().length];
        Arrays.fill(probabilities, 1.0d);
        for (Domain domain : domains) {
            for (int i = 0; i < probabilities.length; i++) {
                probabilities[i] *= domain.getAlignmentProbabilities().getProbabilities().get(i);
            }
        }

        ProbabilityDistributionTable<Alignment> tempPdt = new ProbabilityDistributionTable<Alignment>(Alignment.values(),
                probabilities);

        return tempPdt.getRandom(options.getRandom());
    }

}