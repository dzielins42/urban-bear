package pl.dzielins42.dmtools.model.religion;

import pl.dzielins42.dmtools.model.Alignment;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;

/**
 * Interface to represents aspect of power of {@link Deity}.
 */
public interface Domain {

    /**
     * For each {@link Alignment}, returns probability that deity with this
     * domain will be of given alignment. The values are the same as returned by
     * {@link #getAlignmentProbability(Alignment)} for each Alignment.
     * 
     * @return probabilities of deity with this domain having given
     *         {@link Alignment}.
     */
    ProbabilityDistributionTable<Alignment> getAlignmentProbabilities();

    /**
     * Returns probability of deity with this domain having given
     * {@link Alignment}. The value is the same as in {@link EnumMap} returned
     * by {@link Domain#getAlignmentProbabilities()}.
     * 
     * @param alignment
     *            alignment for which probability should be returned.
     * @return probability of deity with this domain having given
     *         {@link Alignment}.
     */
    double getAlignmentProbability(Alignment alignment);

    /**
     * Returns name of the domain.
     * 
     * @return domain's name.
     */
    String getName();

}