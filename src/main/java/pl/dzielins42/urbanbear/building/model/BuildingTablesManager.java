package pl.dzielins42.urbanbear.building.model;

public interface BuildingTablesManager {

    double getStructuralComponentCostPercentage(StructuralComponent component);
    
    double getStructureTypeBaseCost(StructureType type);

    double getMaterialCostPercentage(Material material);

    double getCarriageModifier(Material material, CarriageType carriageType, double distance);

    double getStyleModifier(Style style, StyleType styleType);
    
    double getHeightModifier(double level);
    
}
