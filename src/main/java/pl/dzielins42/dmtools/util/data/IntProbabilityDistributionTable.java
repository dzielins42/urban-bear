package pl.dzielins42.dmtools.util.data;

import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;

public class IntProbabilityDistributionTable extends ProbabilityDistributionTable<Integer> {

    public IntProbabilityDistributionTable(Integer[] elements, double[] probabilities) {
        super(elements, probabilities);
    }

    public IntProbabilityDistributionTable(Integer[] elements) {
        super(elements);
    }

}
