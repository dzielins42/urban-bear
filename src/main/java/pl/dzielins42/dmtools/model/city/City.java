package pl.dzielins42.dmtools.model.city;

import java.util.List;

public class City {

    private Type type;
    private int population;
    private double area;
    private List<Ward> wards;
    private double gpLimit;
    /**
     * % of wealth that is magical
     */
    private double magicalResources;
    private CityDemographics demographics;

    public City(Type type, int population, double area, List<Ward> wards, double gpLimit, double magicalResources,
            CityDemographics demographics) {
        super();
        this.type = type;
        this.population = population;
        this.area = area;
        this.wards = wards;
        this.gpLimit = gpLimit;
        this.magicalResources = magicalResources;
        this.demographics = demographics;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<Ward> getWards() {
        return wards;
    }

    public void setWards(List<Ward> wards) {
        this.wards = wards;
    }

    public double getGpLimit() {
        return gpLimit;
    }

    public void setGpLimit(double gpLimit) {
        this.gpLimit = gpLimit;
    }

    public double getMagicalResources() {
        return magicalResources;
    }

    public void setMagicalResources(double magicalResources) {
        this.magicalResources = magicalResources;
    }

    public CityDemographics getDemographics() {
        return demographics;
    }

    public void setDemographics(CityDemographics demographics) {
        this.demographics = demographics;
    }

    @Override
    public String toString() {
        return "City [type=" + type + ", population=" + population + ", area=" + area + ", wards=" + wards + ", gpLimit="
                + gpLimit + ", magicalResources=" + magicalResources + ", demographics=" + demographics + "]";
    }

    public double getWealth() {
        return (getPopulation() / 10.0d) * (getGpLimit() / 2.0d);
    }

    public double getMagicalResoucesValue() {
        return getMagicalResources() * getWealth();
    }

    public enum Type {
        THORP, HAMLET, VILLAGE, SMALL_TOWN, LARGE_TOWN, SMALL_CITY, LARGE_CITY, METROPOLIS;
    }

}