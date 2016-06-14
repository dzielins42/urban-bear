package pl.dzielins42.dmtools.generator.city.model.table;

import java.util.Map;

import pl.dzielins42.dmtools.generator.city.model.Ward;
import pl.dzielins42.dmtools.generator.city.model.WardBuilding;

public interface WardBuildingIncidenceTable {

    double getIncidence(Ward.Type wardType, WardBuilding.Type buildingType);

    Map<WardBuilding.Type, Double> getIncidences(Ward.Type wardType);

    Map<Ward.Type, Double> getIncidences(WardBuilding.Type buildingType);

}
