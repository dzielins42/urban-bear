package pl.dzielins42.dmtools.generator.city;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Random;
import java.util.TreeMap;

import pl.dzielins42.dmtools.generator.city.model.Ward;
import pl.dzielins42.dmtools.generator.city.model.WardBuilding;
import pl.dzielins42.dmtools.generator.city.model.table.WardBuildingIncidenceTable;

public class WardGenerator {

    private Random random;
    private WardBuildingIncidenceTable buildingIncidenceTable;

    public WardGenerator(WardBuildingIncidenceTable buildingIncidenceTable, long seed) {
        if (buildingIncidenceTable == null) {
            throw new IllegalArgumentException();
        }

        this.random = new Random(seed);
        this.buildingIncidenceTable = buildingIncidenceTable;
    }

    public WardGenerator(WardBuildingIncidenceTable buildingIncidenceTable) {
        if (buildingIncidenceTable == null) {
            throw new IllegalArgumentException();
        }

        this.random = new Random();
        this.buildingIncidenceTable = buildingIncidenceTable;
    }

    public Ward generateStructures(Ward ward) {
        if (ward.getBuildingCount() <= 0) {
            throw new IllegalStateException(String.valueOf(ward.getBuildingCount()) + " invalid count of building in the ward");
        }
        // Get buildings incidences
        Map<WardBuilding.Type, Double> incidences = buildingIncidenceTable.getIncidences(ward.getType());
        if (incidences == null || incidences.isEmpty()) {
            throw new IllegalStateException("Building incidence table empty");
        }
        // Generate structure to draw from
        NavigableMap<Double, WardBuilding.Type> pot = new TreeMap<Double, WardBuilding.Type>();
        double g = 0;
        for (Map.Entry<WardBuilding.Type, Double> entry : incidences.entrySet()) {
            if (entry.getValue() > 0) {
                pot.put(g, entry.getKey());
                g += entry.getValue();
            }
        }
        // Generate building types
        List<WardBuilding.Type> types = new ArrayList<WardBuilding.Type>(ward.getBuildingCount());
        double q;
        Map.Entry<Double, WardBuilding.Type> e;
        for (int i = 0; i < ward.getBuildingCount(); i++) {
            q = random.nextDouble() * g;
            e = pot.floorEntry(q);
            types.add(e.getValue());
        }

        List<WardBuilding> buildings = new ArrayList<WardBuilding>(ward.getBuildingCount());
        for (int i = 0; i < ward.getBuildingCount(); i++) {
            buildings.add(new WardBuilding(WardBuilding.Style.NORMAL, types.get(i)));
        }

        ward.setBuildings(buildings);

        return ward;
    }

}
