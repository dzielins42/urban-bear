package pl.dzielins42.dmtools.generator.city;

import java.util.ArrayList;
import java.util.List;

import pl.dzielins42.dmtools.model.CharacterClass;
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
        // TODO generate population and area based on city type
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
        int powerCentersCount = options.getPowerCenterNumber().get(cityType).getMin()
                + options.nextInt(options.getPowerCenterNumber().get(cityType).getMax()
                        - options.getPowerCenterNumber().get(cityType).getMin() + 1);
        // Get influence points
        int influencePointsSum = getInfluencePointsSum(demographics);

        return new City(cityType, population, area, wards, gpLimit, magicalResources, demographics);
    }

    private int getInfluencePointsSum(CityDemographics demographics) {
        float influencePointsSum = 0;
        for (CharacterClass cc : CharacterClass.values()) {
            int highestLevel = demographics.getHighestLevel(cc);
            for (int l = highestLevel; highestLevel > 0; highestLevel--) {
                // Each level of PC classes, adept and noble counts as 1 IP
                // Each level of commoner, expert and warrior count as 1/2 IP
                influencePointsSum += ((cc == CharacterClass.COMMONER || cc == CharacterClass.EXPERT
                        || cc == CharacterClass.WARRIOR) ? 0.5f : 1.0f) * (l * demographics.numberOf(cc, l));
            }
        }

        return (int) influencePointsSum;
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
        // Calculate minimum area based on structures distribution in wards for
        // given type of city
        // Ward area cannot be smaller than area needed for at least 1 building
        // Get max min structures/acre
        int maxMinStructuresPerAcre = Integer.MIN_VALUE;
        for (Ward.Type wt : Ward.Type.values()) {
            if (maxMinStructuresPerAcre < options.getStructuresDistribution(cityType, wt).getMin()) {
                maxMinStructuresPerAcre = options.getStructuresDistribution(cityType, wt).getMin();
            }
        }
        // Calculate the value
        double minWardArea = 1.0d / maxMinStructuresPerAcre;
        // Global minWardArea is a little simplification because each ward type
        // may have other min area, however, this value is lower limit - there
        // cannot be smaller ward, this ensures that each ward will area for at
        // least one structure
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