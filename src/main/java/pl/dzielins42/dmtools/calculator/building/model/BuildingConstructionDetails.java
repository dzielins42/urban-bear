package pl.dzielins42.dmtools.calculator.building.model;

import java.util.ArrayList;
import java.util.List;

public class BuildingConstructionDetails {
    private WallThickness wallThickness;
    private boolean requiresDeeperFoundations;
    private List<Section> sections;
    private Material foundationMaterial;
    private Material wallMaterial;
    private Material roofMaterial;
    private List<Carriage> carriages;
    private boolean useFlatCarriage;
    private double flatCarriage;
    private Style externalStyle;
    private Style internalStyle;
    private BuildingSpeed buildingSpeed;

    public WallThickness getWallThickness() {
        return wallThickness;
    }

    public void setWallThickness(WallThickness wallThickness) {
        this.wallThickness = wallThickness;
    }

    public boolean isRequiresDeeperFoundations() {
        return requiresDeeperFoundations;
    }

    public void setRequiresDeeperFoundations(boolean requiresDeeperFoundations) {
        this.requiresDeeperFoundations = requiresDeeperFoundations;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public void addSection(Section section) {
        if (section == null) {
            throw new IllegalArgumentException();
        }

        if (sections == null) {
            sections = new ArrayList<Section>();
        }

        sections.add(section);
    }

    public Material getFoundationMaterial() {
        return foundationMaterial;
    }

    public void setFoundationMaterial(Material foundationMaterial) {
        this.foundationMaterial = foundationMaterial;
    }

    public Material getWallMaterial() {
        return wallMaterial;
    }

    public void setWallMaterial(Material wallMaterial) {
        this.wallMaterial = wallMaterial;
    }

    public Material getRoofMaterial() {
        return roofMaterial;
    }

    public void setRoofMaterial(Material roofMaterial) {
        this.roofMaterial = roofMaterial;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    public boolean isUseFlatCarriage() {
        return useFlatCarriage;
    }

    public void setUseFlatCarriage(boolean useFlatCarriage) {
        this.useFlatCarriage = useFlatCarriage;
    }

    public double getFlatCarriage() {
        return flatCarriage;
    }

    public void setFlatCarriage(double flatCarriage) {
        this.flatCarriage = flatCarriage;
    }

    public Style getExternalStyle() {
        return externalStyle;
    }

    public void setExternalStyle(Style externalStyle) {
        this.externalStyle = externalStyle;
    }

    public Style getInternalStyle() {
        return internalStyle;
    }

    public void setInternalStyle(Style internalStyle) {
        this.internalStyle = internalStyle;
    }

    public BuildingSpeed getBuildingSpeed() {
        return buildingSpeed;
    }

    public void setBuildingSpeed(BuildingSpeed buildingSpeed) {
        this.buildingSpeed = buildingSpeed;
    }

}
