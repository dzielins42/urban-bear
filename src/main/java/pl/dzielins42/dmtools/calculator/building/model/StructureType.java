package pl.dzielins42.dmtools.calculator.building.model;

public enum StructureType {
    AQUEDUCT,
    BARN,
    BARRACK,
    BATH,
    BOARDING_HOUSE,
    BRIDGE,
    CANAL,
    CEMETERY,
    CHURCH,
    CISTERN,
    COLISEUM,
    CORRAL,
    DAM,
    DOCK_EARTH(true),
    DOCK_STONE(true),
    DOVECOTE,
    EXCAVATION_EARTH(true),
    EXCAVATION_EARTH_WOOD_SIDING(true),
    EXCAVATION_EARTH_STONE_SIDING(true),
    EXCAVATION_STONE(true),
    FOUNTAIN,
    GARDEN,
    GRANARY,
    GUILD_HOUSE,
    HARBOR,
    HOSPITAL_ASYLUM,
    HOUSE_KITCHEN,
    INFIRMARY,
    INN_TAVERN,
    LIBRARY,
    LIGHTHOUSE,
    MAUSOLEUM,
    MILL,
    OFFICE_ADMINISTRATION,
    PIER,
    ROAD_PLAZA,
    PRISON,
    SERWER_EARTH(true),
    SEWER_STONE(true),
    SHELL_KEEP_DONJON_TOWER,
    SHOP,
    STABLE,
    TENEMENT,
    THEATRE,
    UNIVERSITY,
    WALL_BRICK_ADOBE,
    WALL_STONE,
    WALL_WOOD,
    WAREHOUSE,
    WORKSHOP;

    private boolean cubicCost = false;

    private StructureType() {
        this(false);
    }

    private StructureType(boolean cubicCost) {
        this.cubicCost = cubicCost;
    }

    public boolean isCubicCost() {
        return cubicCost;
    }
}
