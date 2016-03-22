package pl.dzielins42.urbanbear.test.building;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import pl.dzielins42.urbanbear.building.model.BaseBuildingTablesManager;
import pl.dzielins42.urbanbear.building.model.BuildingConstructionDetails;
import pl.dzielins42.urbanbear.building.model.BuildingCostCalculator;
import pl.dzielins42.urbanbear.building.model.BuildingTablesManager;
import pl.dzielins42.urbanbear.building.model.Material;
import pl.dzielins42.urbanbear.building.model.Section;
import pl.dzielins42.urbanbear.building.model.StructuralComponent;
import pl.dzielins42.urbanbear.building.model.StructureType;
import pl.dzielins42.urbanbear.building.model.Style;
import pl.dzielins42.urbanbear.building.model.StyleType;

public class BuildingTest {

    public static void main(String[] args) {
        BuildingConstructionDetails constructionDetails = new BuildingConstructionDetails();
        BuildingTablesManager tablesManager;

        Map<StructuralComponent, Double> structuralComponentCostPercentageMap = new EnumMap<StructuralComponent, Double>(
                StructuralComponent.class);
        structuralComponentCostPercentageMap.put(StructuralComponent.FOUNDATION, 0.12d);
        structuralComponentCostPercentageMap.put(StructuralComponent.WALL, 0.80d);
        structuralComponentCostPercentageMap.put(StructuralComponent.ROOF, 0.08d);
        Map<StructureType, Double> structureTypeBaseCostMap = new EnumMap<StructureType, Double>(StructureType.class);
        structureTypeBaseCostMap.put(StructureType.AQUEDUCT, 3.5d);
        structureTypeBaseCostMap.put(StructureType.BARN, 0.7d);
        structureTypeBaseCostMap.put(StructureType.BARRACK, 0.8d);
        structureTypeBaseCostMap.put(StructureType.BATH, 2.0d);
        structureTypeBaseCostMap.put(StructureType.BOARDING_HOUSE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.BRIDGE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.CANAL, 0.0d);
        structureTypeBaseCostMap.put(StructureType.CEMETERY, 0.0d);
        structureTypeBaseCostMap.put(StructureType.CHURCH, 0.0d);
        structureTypeBaseCostMap.put(StructureType.CISTERN, 0.0d);
        structureTypeBaseCostMap.put(StructureType.COLISEUM, 0.0d);
        structureTypeBaseCostMap.put(StructureType.CORRAL, 0.0d);
        structureTypeBaseCostMap.put(StructureType.DAM, 0.0d);
        structureTypeBaseCostMap.put(StructureType.DOCK_EARTH, 0.0d);
        structureTypeBaseCostMap.put(StructureType.DOCK_STONE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.DOVECOTE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.EXCAVATION_EARTH, 0.01d);
        structureTypeBaseCostMap.put(StructureType.EXCAVATION_EARTH_WOOD_SIDING, 0.0d);
        structureTypeBaseCostMap.put(StructureType.EXCAVATION_EARTH_STONE_SIDING, 0.0d);
        structureTypeBaseCostMap.put(StructureType.EXCAVATION_STONE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.FOUNTAIN, 0.0d);
        structureTypeBaseCostMap.put(StructureType.GARDEN, 0.0d);
        structureTypeBaseCostMap.put(StructureType.GRANARY, 0.0d);
        structureTypeBaseCostMap.put(StructureType.GUILD_HOUSE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.HARBOR, 0.0d);
        structureTypeBaseCostMap.put(StructureType.HOSPITAL_ASYLUM, 0.0d);
        structureTypeBaseCostMap.put(StructureType.HOUSE_KITCHEN, 1.0d);
        structureTypeBaseCostMap.put(StructureType.INFIRMARY, 0.0d);
        structureTypeBaseCostMap.put(StructureType.INN_TAVERN, 0.0d);
        structureTypeBaseCostMap.put(StructureType.LIBRARY, 0.0d);
        structureTypeBaseCostMap.put(StructureType.LIGHTHOUSE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.MAUSOLEUM, 0.0d);
        structureTypeBaseCostMap.put(StructureType.MILL, 0.0d);
        structureTypeBaseCostMap.put(StructureType.OFFICE_ADMINISTRATION, 0.0d);
        structureTypeBaseCostMap.put(StructureType.PIER, 0.0d);
        structureTypeBaseCostMap.put(StructureType.ROAD_PLAZA, 0.0d);
        structureTypeBaseCostMap.put(StructureType.PRISON, 0.0d);
        structureTypeBaseCostMap.put(StructureType.SERWER_EARTH, 0.0d);
        structureTypeBaseCostMap.put(StructureType.SEWER_STONE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.SHELL_KEEP_DONJON_TOWER, 0.0d);
        structureTypeBaseCostMap.put(StructureType.SHOP, 0.0d);
        structureTypeBaseCostMap.put(StructureType.STABLE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.TENEMENT, 0.0d);
        structureTypeBaseCostMap.put(StructureType.THEATRE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.UNIVERSITY, 0.0d);
        structureTypeBaseCostMap.put(StructureType.WALL_BRICK_ADOBE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.WALL_STONE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.WALL_WOOD, 0.0d);
        structureTypeBaseCostMap.put(StructureType.WAREHOUSE, 0.0d);
        structureTypeBaseCostMap.put(StructureType.WORKSHOP, 0.0d);
        Map<Material, Double> materialCostPercentageMap = new EnumMap<Material, Double>(Material.class);
        materialCostPercentageMap.put(Material.NONE, 0.0d);
        materialCostPercentageMap.put(Material.DIRT, 0.0d);
        materialCostPercentageMap.put(Material.THATCH, 0.005d);
        materialCostPercentageMap.put(Material.WATTLE_AND_DAUB, 0.025d);
        materialCostPercentageMap.put(Material.WOOD, 0.125d);
        materialCostPercentageMap.put(Material.ADOBE, 0.5d);
        materialCostPercentageMap.put(Material.BRICK, 0.8d);
        materialCostPercentageMap.put(Material.STONE, 1.0d);
        materialCostPercentageMap.put(Material.REINFORCED_STONE, 3.0d);
        Table<Style, StyleType, Double> styleModifierTable = ArrayTable.create(Arrays.asList(Style.values()),
                Arrays.asList(StyleType.values()));
        styleModifierTable.put(Style.ROUGH, StyleType.EXTERIOR, 0.0d);
        styleModifierTable.put(Style.FUNCTIONAL, StyleType.EXTERIOR, 0.075d);
        styleModifierTable.put(Style.UTILITARIAN, StyleType.EXTERIOR, 0.1d);
        styleModifierTable.put(Style.BASIC, StyleType.EXTERIOR, 0.125d);
        styleModifierTable.put(Style.NORMAL, StyleType.EXTERIOR, 0.15d);
        styleModifierTable.put(Style.TASTEFUL, StyleType.EXTERIOR, 0.25d);
        styleModifierTable.put(Style.ORNATE, StyleType.EXTERIOR, 0.375d);
        styleModifierTable.put(Style.ARTISTIC, StyleType.EXTERIOR, 0.5d);
        styleModifierTable.put(Style.LUXURIOUS, StyleType.EXTERIOR, 0.625d);
        styleModifierTable.put(Style.ROYAL, StyleType.EXTERIOR, 0.75d);
        styleModifierTable.put(Style.IMPERIAL, StyleType.EXTERIOR, 1.0d);
        styleModifierTable.put(Style.ROUGH, StyleType.INTERIOR, 0.1d);
        styleModifierTable.put(Style.FUNCTIONAL, StyleType.INTERIOR, 0.3d);
        styleModifierTable.put(Style.UTILITARIAN, StyleType.INTERIOR, 0.4d);
        styleModifierTable.put(Style.BASIC, StyleType.INTERIOR, 0.5d);
        styleModifierTable.put(Style.NORMAL, StyleType.INTERIOR, 0.6d);
        styleModifierTable.put(Style.TASTEFUL, StyleType.INTERIOR, 1.0d);
        styleModifierTable.put(Style.ORNATE, StyleType.INTERIOR, 1.5d);
        styleModifierTable.put(Style.ARTISTIC, StyleType.INTERIOR, 2.0d);
        styleModifierTable.put(Style.LUXURIOUS, StyleType.INTERIOR, 2.5d);
        styleModifierTable.put(Style.ROYAL, StyleType.INTERIOR, 3.0d);
        styleModifierTable.put(Style.IMPERIAL, StyleType.INTERIOR, 4.0d);
        NavigableMap<Double, Double> heightModifierMap = new TreeMap<Double, Double>();
        heightModifierMap.put(0.0d,0.0d);
        heightModifierMap.put(16.0d,0.1d);
        heightModifierMap.put(31.0d,0.2d);
        heightModifierMap.put(46.0d,0.3d);
        heightModifierMap.put(61.0d,0.4d);
        heightModifierMap.put(76.0d,0.5d);
        tablesManager = new BaseBuildingTablesManager(structuralComponentCostPercentageMap, structureTypeBaseCostMap,
                materialCostPercentageMap, styleModifierTable, heightModifierMap);

        BuildingCostCalculator calculator = new BuildingCostCalculator(tablesManager);
        double cost = 0;

        constructionDetails.addSection(new Section(StructureType.HOUSE_KITCHEN, 1000.0d, 0.0d, 40.0d));
        constructionDetails.addSection(new Section(StructureType.EXCAVATION_EARTH, 1000.0d, 0.0d, 2.0d));
        constructionDetails.setFoundationMaterial(Material.STONE);
        constructionDetails.setWallMaterial(Material.STONE);
        constructionDetails.setRoofMaterial(Material.WOOD);
        constructionDetails.setExternalStyle(Style.TASTEFUL);
        constructionDetails.setInternalStyle(Style.TASTEFUL);

        cost = calculator.calculateCost(constructionDetails);

        System.out.println(String.valueOf(cost));
    }
}
