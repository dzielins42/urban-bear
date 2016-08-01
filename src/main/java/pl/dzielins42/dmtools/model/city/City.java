package pl.dzielins42.dmtools.model.city;

import java.util.List;

public class City {

    private Type type;
    private int population;
    private double area;
    private List<Ward> wards;
    private double gpLimit;
    private double magicalResources;

    public City(Type type, int population, double area, List<Ward> wards, double gpLimit, double magicalResources) {
        super();
        this.type = type;
        this.population = population;
        this.area = area;
        this.wards = wards;
        this.gpLimit = gpLimit;
        this.magicalResources = magicalResources;
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

    @Override
    public String toString() {
        return "City [type=" + type + ", population=" + population + ", area=" + area + ", gpLimit=" + gpLimit
                + ", magicalResources=" + magicalResources + ", wards=" + wards + "]";
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