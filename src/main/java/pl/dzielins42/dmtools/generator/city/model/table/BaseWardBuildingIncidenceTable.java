package pl.dzielins42.dmtools.generator.city.model.table;

import pl.dzielins42.dmtools.generator.city.model.Ward;
import pl.dzielins42.dmtools.generator.city.model.WardBuilding;
import pl.dzielins42.dmtools.generator.city.model.WardBuilding.Type;

import com.google.common.collect.Table;

public class BaseWardBuildingIncidenceTable extends AbstractWardBuildingIncidenceTable {

    public BaseWardBuildingIncidenceTable(Table<WardBuilding.Type, Ward.Type, Double> incidenceTable) {
        super(incidenceTable);
    }

}
