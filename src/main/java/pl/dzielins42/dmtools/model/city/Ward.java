package pl.dzielins42.dmtools.model.city;

import java.util.List;

public class Ward {

    private Type type;
    /**
     * Ward size in acres.
     */
    private double area;
    private List<WardBuilding> buildings;

    public Ward(Type type, double area, List<WardBuilding> buildings) {
        super();

        // Validate
        if (type == null || area <= 0) {
            throw new IllegalArgumentException();
        }

        this.type = type;
        this.area = area;
        this.buildings = buildings;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        // Validate
        if (type == null) {
            throw new IllegalArgumentException();
        }

        this.type = type;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        // Validate
        if (area <= 0) {
            throw new IllegalArgumentException();
        }

        this.area = area;
    }

    public List<WardBuilding> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<WardBuilding> buildings) {
        this.buildings = buildings;
    }

    public int getBuildingCount() {
        return (buildings == null || buildings.isEmpty()) ? 0 : buildings.size();
    }

    @Override
    public String toString() {
        return "Ward [type=" + type + ", area=" + area + ", buildings=" + buildings + "]";
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
