package pl.dzielins42.dmtools.calculator.building.model;

public class Carriage {

    private Material material;
    private double distanceByLand, distanceByWater;

    public Carriage(Material material, double distanceByLand, double distanceByWater) {
        super();
        this.material = material;
        this.distanceByLand = distanceByLand;
        this.distanceByWater = distanceByWater;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getDistanceByLand() {
        return distanceByLand;
    }

    public void setDistanceByLand(double distanceByLand) {
        this.distanceByLand = distanceByLand;
    }

    public double getDistanceByWater() {
        return distanceByWater;
    }

    public void setDistanceByWater(double distanceByWater) {
        this.distanceByWater = distanceByWater;
    }

}
