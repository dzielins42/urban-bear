package pl.dzielins42.dmtools.generator.city;

import java.util.EnumMap;

import org.javatuples.Pair;

import com.google.common.collect.Table;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.model.city.City;
import pl.dzielins42.dmtools.model.city.Ward;
import pl.dzielins42.dmtools.model.city.WardBuilding;
import pl.dzielins42.dmtools.model.enumeration.CharacterClass;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;
import pl.dzielins42.dmtools.util.RandomGenerator;
import pl.dzielins42.dmtools.util.data.IntMinMaxValues;
import pl.dzielins42.dmtools.util.data.IntProbabilityDistributionTable;

public class CityGeneratorOptions extends GeneratorOptions {

    private ProbabilityDistributionTable<City.Type> cityTypesProbabilities;
    private EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>> wardTypesProbabilities;
    private Table<Ward.Type, City.Type, IntMinMaxValues> wardStructuresDistributions;
    private EnumMap<Ward.Type, ProbabilityDistributionTable<Pair<WardBuilding.Style,WardBuilding.Type>>> structureTypesProbabilities;
    private EnumMap<City.Type, Double> cityTypesGpLimits;
    private EnumMap<City.Type, Double> cityTypesUnabsorbedInfluencePoints;
    private EnumMap<City.Type, Double> cityTypesMagicalResources;
    private Table<City.Type, CharacterClass, IntProbabilityDistributionTable> cityDemographics;
    private EnumMap<City.Type, Integer> cityDemographicsMaxLevelRollsNumber;
    private EnumMap<City.Type,IntMinMaxValues> powerCenterNumber;

    public CityGeneratorOptions(RandomGenerator random) {
        super(random);
    }

    public EnumMap<City.Type, IntMinMaxValues> getPowerCenterNumber() {
        return powerCenterNumber;
    }

    public void setPowerCenterNumber(EnumMap<City.Type, IntMinMaxValues> powerCenterNumber) {
        this.powerCenterNumber = powerCenterNumber;
    }

    public ProbabilityDistributionTable<City.Type> getCityTypesProbabilities() {
        return cityTypesProbabilities;
    }

    public void setCityTypesProbabilities(ProbabilityDistributionTable<City.Type> cityTypesProbabilities) {
        this.cityTypesProbabilities = cityTypesProbabilities;
    }

    public EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>> getWardTypesProbabilities() {
        return wardTypesProbabilities;
    }

    public void setWardTypesProbabilities(EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>> wardTypesProbabilities) {
        this.wardTypesProbabilities = wardTypesProbabilities;
    }

    public Table<Ward.Type, City.Type, IntMinMaxValues> getWardStructuresDistributions() {
        return wardStructuresDistributions;
    }

    public void setWardStructuresDistributions(Table<Ward.Type, City.Type, IntMinMaxValues> wardStructuresDistributions) {
        this.wardStructuresDistributions = wardStructuresDistributions;
    }

    public EnumMap<Ward.Type, ProbabilityDistributionTable<Pair<WardBuilding.Style,WardBuilding.Type>>> getStructureTypesProbabilities() {
        return structureTypesProbabilities;
    }

    public void setStructureTypesProbabilities(
            EnumMap<Ward.Type, ProbabilityDistributionTable<Pair<WardBuilding.Style,WardBuilding.Type>>> structureTypesProbabilities) {
        this.structureTypesProbabilities = structureTypesProbabilities;
    }

    public EnumMap<City.Type, Double> getCityTypesGpLimits() {
        return cityTypesGpLimits;
    }

    public void setCityTypesGpLimits(EnumMap<City.Type, Double> cityTypesGpLimits) {
        this.cityTypesGpLimits = cityTypesGpLimits;
    }

    public EnumMap<City.Type, Double> getCityTypesUnabsorbedInfluencePoints() {
        return cityTypesUnabsorbedInfluencePoints;
    }

    public void setCityTypesUnabsorbedInfluencePoints(EnumMap<City.Type, Double> cityTypesUnabsorbedInfluencePoints) {
        this.cityTypesUnabsorbedInfluencePoints = cityTypesUnabsorbedInfluencePoints;
    }

    public EnumMap<City.Type, Double> getCityTypesMagicalResources() {
        return cityTypesMagicalResources;
    }

    public void setCityTypesMagicalResources(EnumMap<City.Type, Double> cityTypesMagicalResources) {
        this.cityTypesMagicalResources = cityTypesMagicalResources;
    }

    public City.Type getRandomCityType() {
        return getCityTypesProbabilities().getRandom(getRandom());
    }

    public Ward.Type getRandomWardType(City.Type cityType) {
        return getWardTypesProbabilities().get(cityType).getRandom(getRandom());
    }

    public IntMinMaxValues getStructuresDistribution(City.Type cityType, Ward.Type wardType) {
        return getWardStructuresDistributions().get(wardType, cityType);
    }

    public Pair<WardBuilding.Style,WardBuilding.Type> getRandomStructureType(Ward.Type wardType) {
        return getStructureTypesProbabilities().get(wardType).getRandom(getRandom());
    }

    public double getGpLimit(City.Type cityType) {
        return getCityTypesGpLimits().get(cityType);
    }

    public double getUnabsorbedInfluencePoints(City.Type cityType) {
        return getCityTypesUnabsorbedInfluencePoints().get(cityType);
    }

    public double getMagicalResources(City.Type cityType) {
        return getCityTypesMagicalResources().get(cityType);
    }

    public Table<City.Type, CharacterClass, IntProbabilityDistributionTable> getCityDemographics() {
        return cityDemographics;
    }

    public void setCityDemographics(Table<City.Type, CharacterClass, IntProbabilityDistributionTable> cityDemographics) {
        this.cityDemographics = cityDemographics;
    }

    public EnumMap<City.Type, Integer> getCityDemographicsMaxLevelRollsNumber() {
        return cityDemographicsMaxLevelRollsNumber;
    }

    public void setCityDemographicsMaxLevelRollsNumber(EnumMap<City.Type, Integer> cityDemographicsMaxLevelRollsNumber) {
        this.cityDemographicsMaxLevelRollsNumber = cityDemographicsMaxLevelRollsNumber;
    }

    // TODO common barbarian, monk classes

}