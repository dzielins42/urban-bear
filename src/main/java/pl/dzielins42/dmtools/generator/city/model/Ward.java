package pl.dzielins42.dmtools.generator.city.model;

import java.util.List;

public class Ward {

    private Type type;
    private double area;
    private List<WardBuilding> buildings;
    private int buildingCount;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public List<WardBuilding> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<WardBuilding> buildings) {
        this.buildings = buildings;
    }

    public int getBuildingCount() {
        return buildingCount;
    }

    public void setBuildingCount(int buildingCount) {
        this.buildingCount = buildingCount;
    }

    public enum Type {
        PATRICIATE,
        MERCHANT,
        MILITARY,
        ADMINISTRATION,
        ODORIFEROUS_BUSINESS,
        CRAFTSMEN,
        SEA_OCEAN,
        RIVER_BRIDGE,
        MARKET,
        GATE,
        SLUM,
        SHANTY_TOWN;
    }

}
