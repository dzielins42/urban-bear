package pl.dzielins42.urbanbear.building.model;

//TODO calculating time
public class BuildingCostCalculator {

    private BuildingTablesManager tablesManager;

    public BuildingCostCalculator(BuildingTablesManager tablesManager) {
        if (tablesManager == null) {
            throw new IllegalArgumentException();
        }

        this.tablesManager = tablesManager;
    }

    public double calculateCost(BuildingConstructionDetails constructionDetails) {
        double finalCost = 0;

        // Initial Estimate
        double initialEstimate = getInitialCostEstimate(constructionDetails);
        // Base Price
        double basePrice = initialEstimate + (getMaterialModifier(constructionDetails) * initialEstimate);
        // Carriage cost
        double carriageCost = basePrice * getCarriageModifier(constructionDetails);
        // Style cost
        double styleCost = basePrice * getStyleModifier(constructionDetails);
        // Building speed modifier
        double buildingSpeedModifier = 1.0d;
        // Final cost
        finalCost = buildingSpeedModifier * (basePrice + carriageCost + styleCost);

        return finalCost;
    }

    public double getStyleModifier(BuildingConstructionDetails constructionDetails) {
        if (constructionDetails == null) {
            throw new IllegalArgumentException();
        }

        double styleModifier = 0;

        // External
        styleModifier += getStyleModifier(constructionDetails.getExternalStyle(), StyleType.EXTERIOR);
        // Internal
        styleModifier += getStyleModifier(constructionDetails.getInternalStyle(), StyleType.INTERIOR);

        return styleModifier;
    }

    private double getStyleModifier(Style style, StyleType styleType) {
        return tablesManager.getStyleModifier(style, styleType);
    }

    public double getCarriageModifier(BuildingConstructionDetails constructionDetails) {
        if (constructionDetails == null) {
            throw new IllegalArgumentException();
        }

        if (constructionDetails.isUseFlatCarriage() || constructionDetails.getCarriages() == null
                || constructionDetails.getCarriages().isEmpty()) {
            return constructionDetails.getFlatCarriage();
        }

        Carriage tmpCarriage;
        double carriageModifier = 0;

        // Foundation
        tmpCarriage = findCarriageForMaterial(constructionDetails, constructionDetails.getFoundationMaterial());
        if (tmpCarriage == null) {
            // TODO Error
        }
        carriageModifier += getStructuralComponentCostPercentage(StructuralComponent.FOUNDATION)
                * getCarriageModifier(tmpCarriage);
        // Wall
        tmpCarriage = findCarriageForMaterial(constructionDetails, constructionDetails.getWallMaterial());
        if (tmpCarriage == null) {
            // TODO Error
        }
        carriageModifier += getStructuralComponentCostPercentage(StructuralComponent.WALL) * getCarriageModifier(tmpCarriage);
        // Roof
        tmpCarriage = findCarriageForMaterial(constructionDetails, constructionDetails.getRoofMaterial());
        if (tmpCarriage == null) {
            // TODO Error
        }
        carriageModifier += getStructuralComponentCostPercentage(StructuralComponent.ROOF) * getCarriageModifier(tmpCarriage);

        return carriageModifier;
    }

    private double getCarriageModifier(Carriage carriage) {
        double carriageModifier = 0;

        // Land
        carriageModifier += tablesManager.getCarriageModifier(carriage.getMaterial(), CarriageType.LAND,
                carriage.getDistanceByLand());
        // Water
        carriageModifier += tablesManager.getCarriageModifier(carriage.getMaterial(), CarriageType.WATER,
                carriage.getDistanceByWater());

        return carriageModifier;
    }

    private Carriage findCarriageForMaterial(BuildingConstructionDetails constructionDetails, Material material) {
        if (constructionDetails.getCarriages() == null || constructionDetails.getCarriages().isEmpty()) {
            return null;
        } else {
            for (Carriage carriage : constructionDetails.getCarriages()) {
                if (carriage.getMaterial() == material) {
                    return carriage;
                }
            }

            return null;
        }
    }

    public double getMaterialModifier(BuildingConstructionDetails constructionDetails) {
        if (constructionDetails == null) {
            throw new IllegalArgumentException();
        }

        double materialModifier = 0.0d;

        // Foundation
        materialModifier += getStructuralComponentCostPercentage(StructuralComponent.FOUNDATION)
                * getMaterialCostPercentage(constructionDetails.getFoundationMaterial());
        // Wall
        materialModifier += getStructuralComponentCostPercentage(StructuralComponent.WALL)
                * getMaterialCostPercentage(constructionDetails.getWallMaterial());
        // Roof
        materialModifier += getStructuralComponentCostPercentage(StructuralComponent.ROOF)
                * getMaterialCostPercentage(constructionDetails.getRoofMaterial());

        return materialModifier;
    }

    private double getMaterialCostPercentage(Material material) {
        return tablesManager.getMaterialCostPercentage(material);
    }

    private double getStructuralComponentCostPercentage(StructuralComponent component) {
        return tablesManager.getStructuralComponentCostPercentage(component);
    }

    public double getInitialCostEstimate(BuildingConstructionDetails constructionDetails) {
        if (constructionDetails == null) {
            throw new IllegalArgumentException();
        }

        double initialCostEstimate = 0;

        // Sections cost
        if (constructionDetails.getSections() != null && !constructionDetails.getSections().isEmpty()) {
            for (Section section : constructionDetails.getSections()) {
                initialCostEstimate += getSectionCost(section);
            }
        }

        return initialCostEstimate;
    }

    private double getSectionCost(Section section) {
        if (section == null) {
            throw new IllegalArgumentException();
        }

        // TODO underground levels
        double cost;
        double heightDepthModifier = 1;
        if (section.getStructureType().isCubicCost()) {
            cost = section.getArea() * section.getHeight() * tablesManager.getStructureTypeBaseCost(section.getStructureType());
        } else {
            cost = section.getArea() * tablesManager.getStructureTypeBaseCost(section.getStructureType());
        }
        heightDepthModifier = tablesManager.getHeightModifier(section.getBaseLevel() + section.getHeight());
        cost += cost * heightDepthModifier;

        return cost;
    }

}
