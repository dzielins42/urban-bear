package pl.dzielins42.dmtools.generator.city.model;

public class WardBuilding {
    
    private Style style;
    private Type type;

    public WardBuilding(Style style, Type type) {
        super();
        this.style = style;
        this.type = type;
    }

    @Override
    public String toString() {
        return "WardBuilding [style=" + style + ", type=" + type + "]";
    }

    public enum Style {
        ROUGH, NORMAL, ORNATE, LUXURIOUS;
    }

    public enum Type {
        ADMIN,
        ASYLUM,
        BARRACK,
        BATH,
        BOARDING_HOUSE,
        CEMETARY,
        RELIGIOUS,
        CISTERN,
        COLISEUM,
        CORRAL,
        FOUNTAIN,
        GARDEN,
        GRANERY,
        GUILD_HOUSE,
        HOSPITAL,
        HOUSE,
        INFIRMARY,
        INN,
        LIBRARY,
        MILL,
        OFFICE,
        PLAZA,
        PRISON,
        RESTAURANT,
        SHOP,
        STABLE,
        TAVERN,
        TENEMENT,
        THEATER,
        UNIVERSITY,
        WAREHOUSE,
        WELL,
        WORKSHOP;
    }

}
