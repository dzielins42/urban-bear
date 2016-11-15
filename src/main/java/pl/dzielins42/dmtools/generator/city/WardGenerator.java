package pl.dzielins42.dmtools.generator.city;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

import pl.dzielins42.dmtools.model.city.City;
import pl.dzielins42.dmtools.model.city.Ward;
import pl.dzielins42.dmtools.model.city.WardBuilding;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;
import pl.dzielins42.dmtools.util.data.IntMinMaxValues;

public class WardGenerator {

    public Ward generate(double area, City.Type cityType, CityGeneratorOptions options) {
        // Validate
        if (area <= 0 || options == null || cityType == null) {
            throw new IllegalArgumentException();
        }

        // Get ward type
        Ward.Type wardType = options.getWardTypesProbabilities().get(cityType).getRandom(options.getRandom());

        return generate(wardType, area, cityType, options);
    }

    public Ward generate(Ward.Type wardType, double area, City.Type cityType, CityGeneratorOptions options) {
        // Validate
        if (area <= 0 || options == null || cityType == null || wardType == null) {
            throw new IllegalArgumentException();
        }

        // Get number of buildings
        IntMinMaxValues structDistr = options.getWardStructuresDistributions().get(wardType, cityType);
        // This is number per acre, adjust to ward area
        int structNumber = (int) (structDistr.getMin() + options.nextDouble() * (structDistr.getMax() - structDistr.getMin()));
        structNumber *= area;
        // Generate buildings
        List<WardBuilding> buildings = new ArrayList<WardBuilding>(structNumber);
        for (int i = 0; i < structNumber; i++) {
            buildings.add(generateWardBuilding(wardType, options));
        }

        return new Ward(wardType, area, buildings);
    }

    private WardBuilding generateWardBuilding(Ward.Type wardType, CityGeneratorOptions options) {
        ProbabilityDistributionTable<Pair<WardBuilding.Style,WardBuilding.Type>> probabilities = options.getStructureTypesProbabilities()
                .get(wardType);
        Pair<WardBuilding.Style,WardBuilding.Type> styleTypePair = probabilities.getRandom(options.getRandom());

        return new WardBuilding(styleTypePair.getValue0(),styleTypePair.getValue1());
    }

}