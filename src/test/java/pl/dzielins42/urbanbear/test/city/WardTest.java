package pl.dzielins42.urbanbear.test.city;

import java.util.Arrays;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;

import pl.dzielins42.dmtools.generator.city.WardGenerator;
import pl.dzielins42.dmtools.generator.city.model.Ward;
import pl.dzielins42.dmtools.generator.city.model.WardBuilding;
import pl.dzielins42.dmtools.generator.city.model.table.BaseWardBuildingIncidenceTable;
import pl.dzielins42.dmtools.generator.city.model.table.WardBuildingIncidenceTable;

public class WardTest {

    public static void main(String[] args) {
        Ward ward;
        WardBuildingIncidenceTable wardBuildingIncidenceTable;
        WardGenerator wardGenerator;

        Table<WardBuilding.Type, Ward.Type, Double> incidenceTable = ArrayTable.create(
                Arrays.asList(WardBuilding.Type.values()), Arrays.asList(Ward.Type.values()));

        incidenceTable.put(WardBuilding.Type.ADMIN, Ward.Type.PATRICIATE, 0.02d);
        incidenceTable.put(WardBuilding.Type.ASYLUM, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.BARRACK, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.BATH, Ward.Type.PATRICIATE, 0.05d);
        incidenceTable.put(WardBuilding.Type.BOARDING_HOUSE, Ward.Type.PATRICIATE, 0.01d);
        incidenceTable.put(WardBuilding.Type.CEMETARY, Ward.Type.PATRICIATE, 0.01d);
        incidenceTable.put(WardBuilding.Type.RELIGIOUS, Ward.Type.PATRICIATE, 0.05d);
        incidenceTable.put(WardBuilding.Type.CISTERN, Ward.Type.PATRICIATE, 0.01d);
        incidenceTable.put(WardBuilding.Type.COLISEUM, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.CORRAL, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.FOUNTAIN, Ward.Type.PATRICIATE, 0.02d);
        incidenceTable.put(WardBuilding.Type.GARDEN, Ward.Type.PATRICIATE, 0.02d);
        incidenceTable.put(WardBuilding.Type.GRANERY, Ward.Type.PATRICIATE, 0.01d);
        incidenceTable.put(WardBuilding.Type.GUILD_HOUSE, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.HOSPITAL, Ward.Type.PATRICIATE, 0.02d);
        incidenceTable.put(WardBuilding.Type.HOUSE, Ward.Type.PATRICIATE, 0.22d);
        incidenceTable.put(WardBuilding.Type.INFIRMARY, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.INN, Ward.Type.PATRICIATE, 0.05d);
        incidenceTable.put(WardBuilding.Type.LIBRARY, Ward.Type.PATRICIATE, 0.02d);
        incidenceTable.put(WardBuilding.Type.MILL, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.OFFICE, Ward.Type.PATRICIATE, 0.05d);
        incidenceTable.put(WardBuilding.Type.PLAZA, Ward.Type.PATRICIATE, 0.01d);
        incidenceTable.put(WardBuilding.Type.PRISON, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.RESTAURANT, Ward.Type.PATRICIATE, 0.04d);
        incidenceTable.put(WardBuilding.Type.SHOP, Ward.Type.PATRICIATE, 0.1d);
        incidenceTable.put(WardBuilding.Type.STABLE, Ward.Type.PATRICIATE, 0.05d);
        incidenceTable.put(WardBuilding.Type.TAVERN, Ward.Type.PATRICIATE, 0.1d);
        incidenceTable.put(WardBuilding.Type.TENEMENT, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.THEATER, Ward.Type.PATRICIATE, 0.0d);
        incidenceTable.put(WardBuilding.Type.UNIVERSITY, Ward.Type.PATRICIATE, 0.01d);
        incidenceTable.put(WardBuilding.Type.WAREHOUSE, Ward.Type.PATRICIATE, 0.12d);
        incidenceTable.put(WardBuilding.Type.WELL, Ward.Type.PATRICIATE, 0.01d);
        incidenceTable.put(WardBuilding.Type.WORKSHOP, Ward.Type.PATRICIATE, 0.0d);

        incidenceTable.put(WardBuilding.Type.ADMIN, Ward.Type.ADMINISTRATION, 0.1d);
        incidenceTable.put(WardBuilding.Type.ASYLUM, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.BARRACK, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.BATH, Ward.Type.ADMINISTRATION, 0.04d);
        incidenceTable.put(WardBuilding.Type.BOARDING_HOUSE, Ward.Type.ADMINISTRATION, 0.02d);
        incidenceTable.put(WardBuilding.Type.CEMETARY, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.RELIGIOUS, Ward.Type.ADMINISTRATION, 0.04d);
        incidenceTable.put(WardBuilding.Type.CISTERN, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.COLISEUM, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.CORRAL, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.FOUNTAIN, Ward.Type.ADMINISTRATION, 0.02d);
        incidenceTable.put(WardBuilding.Type.GARDEN, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.GRANERY, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.GUILD_HOUSE, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.HOSPITAL, Ward.Type.ADMINISTRATION, 0.04d);
        incidenceTable.put(WardBuilding.Type.HOUSE, Ward.Type.ADMINISTRATION, 0.16d);
        incidenceTable.put(WardBuilding.Type.INFIRMARY, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.INN, Ward.Type.ADMINISTRATION, 0.05d);
        incidenceTable.put(WardBuilding.Type.LIBRARY, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.MILL, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.OFFICE, Ward.Type.ADMINISTRATION, 0.05d);
        incidenceTable.put(WardBuilding.Type.PLAZA, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.PRISON, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.RESTAURANT, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.SHOP, Ward.Type.ADMINISTRATION, 0.05d);
        incidenceTable.put(WardBuilding.Type.STABLE, Ward.Type.ADMINISTRATION, 0.05d);
        incidenceTable.put(WardBuilding.Type.TAVERN, Ward.Type.ADMINISTRATION, 0.1d);
        incidenceTable.put(WardBuilding.Type.TENEMENT, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.THEATER, Ward.Type.ADMINISTRATION, 0.0d);
        incidenceTable.put(WardBuilding.Type.UNIVERSITY, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.WAREHOUSE, Ward.Type.ADMINISTRATION, 0.07d);
        incidenceTable.put(WardBuilding.Type.WELL, Ward.Type.ADMINISTRATION, 0.01d);
        incidenceTable.put(WardBuilding.Type.WORKSHOP, Ward.Type.ADMINISTRATION, 0.12d);

        incidenceTable.put(WardBuilding.Type.ADMIN, Ward.Type.MARKET, 0.05d);
        incidenceTable.put(WardBuilding.Type.ASYLUM, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.BARRACK, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.BATH, Ward.Type.MARKET, 0.04d);
        incidenceTable.put(WardBuilding.Type.BOARDING_HOUSE, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.CEMETARY, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.RELIGIOUS, Ward.Type.MARKET, 0.06d);
        incidenceTable.put(WardBuilding.Type.CISTERN, Ward.Type.MARKET, 0.01d);
        incidenceTable.put(WardBuilding.Type.COLISEUM, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.CORRAL, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.FOUNTAIN, Ward.Type.MARKET, 0.02d);
        incidenceTable.put(WardBuilding.Type.GARDEN, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.GRANERY, Ward.Type.MARKET, 0.01d);
        incidenceTable.put(WardBuilding.Type.GUILD_HOUSE, Ward.Type.MARKET, 0.01d);
        incidenceTable.put(WardBuilding.Type.HOSPITAL, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.HOUSE, Ward.Type.MARKET, 0.06d);
        incidenceTable.put(WardBuilding.Type.INFIRMARY, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.INN, Ward.Type.MARKET, 0.05d);
        incidenceTable.put(WardBuilding.Type.LIBRARY, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.MILL, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.OFFICE, Ward.Type.MARKET, 0.05d);
        incidenceTable.put(WardBuilding.Type.PLAZA, Ward.Type.MARKET, 0.01d);
        incidenceTable.put(WardBuilding.Type.PRISON, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.RESTAURANT, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.SHOP, Ward.Type.MARKET, 0.21d);
        incidenceTable.put(WardBuilding.Type.STABLE, Ward.Type.MARKET, 0.05d);
        incidenceTable.put(WardBuilding.Type.TAVERN, Ward.Type.MARKET, 0.15d);
        incidenceTable.put(WardBuilding.Type.TENEMENT, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.THEATER, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.UNIVERSITY, Ward.Type.MARKET, 0.0d);
        incidenceTable.put(WardBuilding.Type.WAREHOUSE, Ward.Type.MARKET, 0.21d);
        incidenceTable.put(WardBuilding.Type.WELL, Ward.Type.MARKET, 0.01d);
        incidenceTable.put(WardBuilding.Type.WORKSHOP, Ward.Type.MARKET, 0.0d);
        
        incidenceTable.put(WardBuilding.Type.ADMIN, Ward.Type.MERCHANT, 0.02d);
        incidenceTable.put(WardBuilding.Type.ASYLUM, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.BARRACK, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.BATH, Ward.Type.MERCHANT, 0.03d);
        incidenceTable.put(WardBuilding.Type.BOARDING_HOUSE, Ward.Type.MERCHANT, 0.02d);
        incidenceTable.put(WardBuilding.Type.CEMETARY, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.RELIGIOUS, Ward.Type.MERCHANT, 0.04d);
        incidenceTable.put(WardBuilding.Type.CISTERN, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.COLISEUM, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.CORRAL, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.FOUNTAIN, Ward.Type.MERCHANT, 0.02d);
        incidenceTable.put(WardBuilding.Type.GARDEN, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.GRANERY, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.GUILD_HOUSE, Ward.Type.MERCHANT, 0.02d);
        incidenceTable.put(WardBuilding.Type.HOSPITAL, Ward.Type.MERCHANT, 0.04d);
        incidenceTable.put(WardBuilding.Type.HOUSE, Ward.Type.MERCHANT, 0.12d);
        incidenceTable.put(WardBuilding.Type.INFIRMARY, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.INN, Ward.Type.MERCHANT, 0.05d);
        incidenceTable.put(WardBuilding.Type.LIBRARY, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.MILL, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.OFFICE, Ward.Type.MERCHANT, 0.05d);
        incidenceTable.put(WardBuilding.Type.PLAZA, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.PRISON, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.RESTAURANT, Ward.Type.MERCHANT, 0.02d);
        incidenceTable.put(WardBuilding.Type.SHOP, Ward.Type.MERCHANT, 0.15d);
        incidenceTable.put(WardBuilding.Type.STABLE, Ward.Type.MERCHANT, 0.05d);
        incidenceTable.put(WardBuilding.Type.TAVERN, Ward.Type.MERCHANT, 0.1d);
        incidenceTable.put(WardBuilding.Type.TENEMENT, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.THEATER, Ward.Type.MERCHANT, 0.0d);
        incidenceTable.put(WardBuilding.Type.UNIVERSITY, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.WAREHOUSE, Ward.Type.MERCHANT, 0.1d);
        incidenceTable.put(WardBuilding.Type.WELL, Ward.Type.MERCHANT, 0.01d);
        incidenceTable.put(WardBuilding.Type.WORKSHOP, Ward.Type.MERCHANT, 0.09d);

        wardBuildingIncidenceTable = new BaseWardBuildingIncidenceTable(incidenceTable);
        wardGenerator=new WardGenerator(wardBuildingIncidenceTable);
        ward=new Ward();
        ward.setType(Ward.Type.PATRICIATE);
        ward.setBuildingCount(100);
        
        wardGenerator.generateStructures(ward);
        
        System.out.println(String.valueOf(ward.getBuildings()));
    }

}
