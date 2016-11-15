package pl.dzielins42.dmtools.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProbabilityDistributionTable<T> {

    private List<T> elements;
    private List<Double> probabilities;

    public ProbabilityDistributionTable(Map<T, Double> map) {
        if (map == null || map.isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.elements = new ArrayList<T>(map.size());
        this.probabilities = new ArrayList<Double>(map.size());
        double[] p = new double[map.size()];
        int i = 0;
        for (Map.Entry<T, Double> entry : map.entrySet()) {
            this.elements.add(entry.getKey());
            p[i] = entry.getValue();
            i++;
        }
        p = normalize(p);
        for (i = 0; i < p.length; i++) {
            this.probabilities.add(p[i]);
        }

        if (!validateData(this.elements, this.probabilities)) {
            throw new IllegalArgumentException();
        }
    }
    
    public ProbabilityDistributionTable(List<T> elements,List<Double> probabilities) {
        if (!validateData(elements, probabilities)) {
            throw new IllegalArgumentException();
        }

        this.elements = elements;
        this.probabilities = probabilities;
    }

    public ProbabilityDistributionTable(T[] elements) {
        if (elements == null || elements.length <= 0) {
            throw new IllegalArgumentException();
        }

        this.elements = new ArrayList<T>(elements.length);
        this.probabilities = new ArrayList<Double>(elements.length);
        double[] p = new double[elements.length];
        Arrays.fill(p, 1.0d / elements.length);
        p = normalize(p);
        for (int i = 0; i < p.length; i++) {
            this.elements.add(elements[i]);
            this.probabilities.add(p[i]);
        }

        if (!validateData(this.elements, this.probabilities)) {
            throw new IllegalArgumentException();
        }
    }

    public ProbabilityDistributionTable(T[] elements, double[] probabilities) {
        this.elements = new ArrayList<T>(elements.length);
        this.probabilities = new ArrayList<Double>(elements.length);
        double[] p = normalize(probabilities);
        for (int i = 0; i < p.length; i++) {
            this.elements.add(elements[i]);
            this.probabilities.add(p[i]);
        }

        if (!validateData(this.elements, this.probabilities)) {
            throw new IllegalArgumentException();
        }
    }

    public List<T> getElements() {
        return elements;
    }

    public List<Double> getProbabilities() {
        return probabilities;
    }

    public double getProbability(T element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).equals(element)) {
                return probabilities.get(i);
            }
        }

        return 0.0d;
    }

    public T getRandom(RandomGenerator random) {
        final double randomValue = random.nextDouble();
        double sum = 0;

        for (int i = 0; i < probabilities.size(); i++) {
            sum += probabilities.get(i);
            if (randomValue < sum) {
                return elements.get(i);
            }
        }

        // Should not happen, but may be caused by floating point inequality
        // problem
        return elements.get(elements.size() - 1);
    }

    public Map<T, Double> asMap() {
        Map<T, Double> map = new LinkedHashMap<T, Double>();
        for (int i = 0; i < elements.size(); i++) {
            map.put(elements.get(i), probabilities.get(i));
        }

        return map;
    }

    protected boolean validateData(List<T> elements, List<Double> probabilities) {
        if (elements == null || probabilities == null || elements.isEmpty() || probabilities.isEmpty()
                || elements.size() != probabilities.size()) {
            return false;
        }

        final int len = elements.size();
        double sum = 0d;
        for (int i = 0; i < len; i++) {
            if (probabilities.get(i) < 0 || Double.isNaN(probabilities.get(i)) || Double.isInfinite(probabilities.get(i))) {
                return false;
            }
            if (elements.get(i) == null) {
                return false;
            }
            sum += probabilities.get(i);
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
        return "ProbabilityDistributionTable [elements=" + String.valueOf(elements) + ", probabilities="
                + String.valueOf(probabilities) + "]";
    }

}