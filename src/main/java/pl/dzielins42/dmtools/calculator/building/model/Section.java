package pl.dzielins42.dmtools.calculator.building.model;

public class Section {

    private StructureType structureType;
    private double area;
    private double baseLevel;
    private double height;

    public Section(StructureType structureType, double area, double baseLevel, double height) {
        super();
        this.structureType = structureType;
        this.area = area;
        this.baseLevel = baseLevel;
        this.height = height;
    }

    public StructureType getStructureType() {
        return structureType;
    }

    public void setStructureType(StructureType structureType) {
        this.structureType = structureType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getBaseLevel() {
        return baseLevel;
    }

    public void setBaseLevel(double baseLevel) {
        this.baseLevel = baseLevel;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

}
