package pl.dzielins42.urbanbear.building.model;

import java.util.Map;
import java.util.NavigableMap;

import com.google.common.collect.Table;

public class BaseBuildingTablesManager extends AbstractBuildingTablesManager {

    public BaseBuildingTablesManager(Map<StructuralComponent, Double> structuralComponentCostPercentageMap,
            Map<StructureType, Double> structureTypeBaseCostMap, Map<Material, Double> materialCostPercentageMap,
            Table<Style, StyleType, Double> styleModifierTable, NavigableMap<Double, Double> heightModifierMap) {
        super(structuralComponentCostPercentageMap, structureTypeBaseCostMap, materialCostPercentageMap, styleModifierTable,
                heightModifierMap);
    }

}
