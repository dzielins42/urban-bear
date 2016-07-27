package pl.dzielins42.dmtools.util;

import java.util.Arrays;

public class ProbabilityDistributionTable<T> {
    private T[] elements;
    private double[] probabilities;

    public ProbabilityDistributionTable(T[] elements) {
        if (elements == null || elements.length <= 0) {
            throw new IllegalArgumentException();
        }
        this.elements = elements;
        double[] p = new double[elements.length];
        Arrays.fill(p, 1.0d / this.elements.length);
        probabilities = normalize(p);

        if (!validateData(elements, probabilities)) {
            throw new IllegalArgumentException();
        }
    }

    public ProbabilityDistributionTable(T[] elements, double[] probabilities) {
        if (!validateData(elements, probabilities)) {
            throw new IllegalArgumentException();
        }
        this.elements = elements;
        this.probabilities = normalize(probabilities);
    }

    public T[] getElements() {
        return elements;
    }

    public double[] getProbabilities() {
        return probabilities;
    }

    public double getProbability(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < elements.length; i++) {
            if (elements[i].equals(element)) {
                return probabilities[i];
            }
        }

        return 0.0d;
    }

    public T getRandom(RandomGenerator random) {
        final double randomValue = random.nextDouble();
        double sum = 0;

        for (int i = 0; i < probabilities.length; i++) {
            sum += probabilities[i];
            if (randomValue < sum) {
                return elements[i];
            }
        }

        // Should not happen, but may be caused by floating point inequality
        // problem
        return elements[elements.length - 1];
    }

    protected boolean validateData(T[] elements, double[] probabilities) {
        if (elements == null || probabilities == null || elements.length <= 0 || probabilities.length <= 0
                || elements.length != probabilities.length) {
            return false;
        }

        final int len = elements.length;
        double sum = 0d;
        for (int i = 0; i < len; i++) {
            if (probabilities[i] < 0 || Double.isNaN(probabilities[i]) || Double.isInfinite(probabilities[i])) {
                return false;
            }
            if (elements[i] == null) {
                return false;
            }
            sum += probabilities[i];
        }
        if (sum <= 0) {
            return false;
        }

        return true;
    }

    protected double[] normalize(double[] values) throws IllegalArgumentException {
        double sum = 0d;
        final int len = values.length;
        double[] out = new double[len];
        for (int i = 0; i < len; i++) {
            if (values[i] < 0 || Double.isNaN(values[i]) || Double.isInfinite(values[i])) {
                throw new IllegalArgumentException();
            }
            sum += values[i];
        }
        if (sum == 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < len; i++) {
            out[i] = values[i] * 1.0d / sum;
        }
        return out;
    }

    @Override
    public String toString() {
        return "ProbabilityDistributionTable [elements=" + Arrays.toString(elements) + ", probabilities="
                + Arrays.toString(probabilities) + "]";
    }

}