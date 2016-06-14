package pl.dzielins42.dmtools.calculator.building.model;

import java.util.Map;
import java.util.NavigableMap;

import com.google.common.collect.Table;

public abstract class AbstractBuildingTablesManager implements BuildingTablesManager {

    protected Map<StructuralComponent, Double> structuralComponentCostPercentageMap;
    protected Map<StructureType, Double> structureTypeBaseCostMap;
    protected Map<Material, Double> materialCostPercentageMap;
    protected Table<Style, StyleType, Double> styleModifierTable;
    protected NavigableMap<Double, Double> heightModifierMap;

    public AbstractBuildingTablesManager() {
        super();
    }

    public AbstractBuildingTablesManager(Map<StructuralComponent, Double> structuralComponentCostPercentageMap,
            Map<StructureType, Double> structureTypeBaseCostMap, Map<Material, Double> materialCostPercentageMap,
            Table<Style, StyleType, Double> styleModifierTable, NavigableMap<Double, Double> heightModifierMap) {
        super();
        this.structuralComponentCostPercentageMap = structuralComponentCostPercentageMap;
        this.structureTypeBaseCostMap = structureTypeBaseCostMap;
        this.materialCostPercentageMap = materialCostPercentageMap;
        this.styleModifierTable = styleModifierTable;
        this.heightModifierMap = heightModifierMap;
    }

    public double getStructuralComponentCostPercentage(StructuralComponent component) {
        if (structuralComponentCostPercentageMap == null || !structuralComponentCostPercentageMap.containsKey(component)) {
            throw new IllegalStateException();
        }

        return structuralComponentCostPercentageMap.get(component);
    }

    public double getStructureTypeBaseCost(StructureType type) {
        if (structureTypeBaseCostMap == null || !structureTypeBaseCostMap.containsKey(type)) {
            throw new IllegalStateException();
        }

        return structureTypeBaseCostMap.get(type);
    }

    public double getMaterialCostPercentage(Material material) {
        if (materialCostPercentageMap == null || !materialCostPercentageMap.containsKey(material)) {
            throw new IllegalStateException();
        }

        return materialCostPercentageMap.get(material);
    }

    // TODO
    public double getCarriageModifier(Material material, CarriageType carriageType, double distance) {
        // TODO Auto-generated method stub
        return 0;
    }

    public double getStyleModifier(Style style, StyleType styleType) {
        if (styleModifierTable == null) {
            throw new IllegalStateException();
        }

        return styleModifierTable.get(style, styleType);
    }

    public double getHeightModifier(double level) {
        if (heightModifierMap == null) {
            throw new IllegalStateException();
        }

        Map.Entry<Double, Double> entry = heightModifierMap.floorEntry(level);
        if (entry != null) {
            return entry.getValue();
        } else {
            return 0.0d;
        }
    }

}
