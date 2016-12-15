package pl.dzielins42.dmtools.model.religion;

import com.google.common.base.Strings;

import pl.dzielins42.dmtools.model.enumeration.Alignment;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;

public class BasicDomain implements Domain {

    private String name;
    private ProbabilityDistributionTable<Alignment> alignmentProbabilities;

    public BasicDomain(String name, ProbabilityDistributionTable<Alignment> alignmentProbabilities) {
        if (Strings.isNullOrEmpty(name) || alignmentProbabilities == null) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.alignmentProbabilities = alignmentProbabilities;
    }

    public BasicDomain(Domain base) {
        this(base.getName(), base.getAlignmentProbabilities());
    }

    public ProbabilityDistributionTable<Alignment> getAlignmentProbabilities() {
        return alignmentProbabilities;
    }

    public void setAlignmentProbabilities(ProbabilityDistributionTable<Alignment> alignmentProbabilities) {
        this.alignmentProbabilities = alignmentProbabilities;
    }

    public double getAlignmentProbability(Alignment alignment) {
        return alignmentProbabilities.getProbability(alignment);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BasicDomain [name=" + name + ", alignmentProbabilities=" + alignmentProbabilities + "]";
    }

}
