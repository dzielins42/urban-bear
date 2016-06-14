package pl.dzielins42.dmtools.generator.city.model.table;

import java.util.Map;

import com.google.common.collect.Table;

import pl.dzielins42.dmtools.generator.city.model.Ward;
import pl.dzielins42.dmtools.generator.city.model.WardBuilding;
import pl.dzielins42.dmtools.generator.city.model.Ward.Type;

public class AbstractWardBuildingIncidenceTable implements WardBuildingIncidenceTable {

    protected Table<WardBuilding.Type, Ward.Type, Double> incidenceTable;

    public AbstractWardBuildingIncidenceTable(
            Table<pl.dzielins42.dmtools.generator.city.model.WardBuilding.Type, Type, Double> incidenceTable) {
        super();
        this.incidenceTable = incidenceTable;
    }

    public double getIncidence(Ward.Type wardType, WardBuilding.Type buildingType) {
        if (incidenceTable == null || incidenceTable.isEmpty() || !incidenceTable.contains(buildingType, wardType)) {
            return 0.0d;
        } else {
            return incidenceTable.get(buildingType, wardType);
        }
    }

    public Map<WardBuilding.Type, Double> getIncidences(Type wardType) {
        if (incidenceTable == null || incidenceTable.isEmpty()) {
            return null;
        } else {
            return incidenceTable.column(wardType);
        }
    }

    public Map<Ward.Type, Double> getIncidences(WardBuilding.Type buildingType) {
        if (incidenceTable == null || incidenceTable.isEmpty()) {
            return null;
        } else {
            return incidenceTable.row(buildingType);
        }
    }

}
