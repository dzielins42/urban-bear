package pl.dzielins42.dmtools.generator.city;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import pl.dzielins42.dmtools.model.city.City;
import pl.dzielins42.dmtools.model.city.CityDemographics;
import pl.dzielins42.dmtools.model.city.Ward;

public class CityGenerator {

    private WardGenerator wardGenerator;
    private CityDemographicsGenerator cityDemographicsGenerator;

    public CityGenerator(WardGenerator wardGenerator, CityDemographicsGenerator cityDemographicsGenerator) {
        super();
        this.wardGenerator = wardGenerator;
        this.cityDemographicsGenerator = cityDemographicsGenerator;
    }

    public City generate(CityGeneratorOptions options) {
        return generate(options.getRandomCityType(), options);
    }

    public City generate(City.Type cityType, CityGeneratorOptions options) {
        // Generate population and area based on city type
        return generate(cityType, 80, 10.0d, options);
    }

    public City generate(City.Type cityType, int population, CityGeneratorOptions options) {
        // TODO generate area based on population and city type
        return generate(cityType, population, 10.0d, options);
    }

    public City generate(City.Type cityType, double area, CityGeneratorOptions options) {
        // TODO generate population based on area and city type
        return generate(cityType, 80, area, options);
    }

    public City generate(City.Type cityType, int population, double area, CityGeneratorOptions options) {
        // Get gp limit
        double gpLimit = getGpLimit(cityType, options);
        // Get magical resources
        double magicalResources = getMagicalResurces(cityType, options);
        // Get wards areas
        double[] wardsAreas = assignAreasToWards(area, cityType, options);
        // Get wards
        List<Ward> wards = new ArrayList<Ward>(wardsAreas.length);
        for (int i = 0; i < wardsAreas.length; i++) {
            wards.add(wardGenerator.generate(wardsAreas[i], cityType, options));
        }
        // Get demographics
        CityDemographics demographics = cityDemographicsGenerator.generate(cityType, population, options);
        // Get power centers
        // Get influence points
        // TODO need to generate demographics first

        return new City(cityType, population, area, wards, gpLimit, magicalResources, demographics);
    }

    private double getGpLimit(City.Type cityType, CityGeneratorOptions options) {
        double base = options.getGpLimit(cityType);
        double deviation = options.nextGaussian() * 0.2d;

        return base + (deviation * base);
    }

    private double getMagicalResurces(City.Type cityType, CityGeneratorOptions options) {
        double base = options.getMagicalResources(cityType);
        double deviation = options.nextGaussian() * 0.1d;

        return base * (deviation + 1);
    }

    private double[] assignAreasToWards(double cityArea, City.Type cityType, CityGeneratorOptions options) {
        // Calculate minimum area based on structures distribution in wards
        // Ward area cannot be smaller than 0 or some wards may not have
        // structures
        double minWardArea = Double.MAX_VALUE;
        for (Ward.Type wt : Ward.Type.values()) {
            int tmp;
            tmp = options.getStructuresDistribution(cityType, wt).getMin();
            if (tmp <= 0) {
                continue;
            }
            if (minWardArea > (1.0d / tmp)) {
                minWardArea = (1.0d / tmp);
            }
        }
        // Calculate maximum number of wards
        int maxWardsCount = (int) Math.floor(cityArea / minWardArea);
        // Get number of wards
        // TODO do it
        int wardsCount = 5;
        // This should not happen
        // Even with this we may end up with bunch of single-structure wards
        if (wardsCount > maxWardsCount) {
            wardsCount = maxWardsCount;
        }
        double[] wardsAreas = new double[wardsCount];
        // Split area equally, decrease area by random value afterwards and put
        // it into unusedArea
        double unusedArea = 0.0d;
        for (int i = 0; i < wardsCount; i++) {
            double tmp;
            wardsAreas[i] = cityArea / wardsCount;
            tmp = options.nextDouble() * (wardsAreas[i] - minWardArea);
            unusedArea += tmp;
            wardsAreas[i] -= tmp;
        }
        // Split unusedArea randomly
        // Random parts of unusedArea should not be too big or single ward may
        // get very big chunk
        while (unusedArea > 0.0d) {
            double tmp;
            if (unusedArea > minWardArea) {
                tmp = options.nextDouble() * (Math.min(cityArea / wardsCount, unusedArea));
            } else {
                tmp = unusedArea;
            }
            unusedArea -= tmp;
            wardsAreas[options.nextInt(wardsCount)] += tmp;
        }

        return wardsAreas;
    }

}