package pl.dzielins42.dmtools.model.city;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import pl.dzielins42.dmtools.model.CharacterClass;

public class CityDemographics {

    private Table<CharacterClass, Integer, Integer> demographics;

    public CityDemographics(Table<CharacterClass, Integer, Integer> demographics) {
        super();
        this.demographics = demographics;
    }

    public void add(CharacterClass characterClass, int level, int count) {
        if (demographics == null) {
            demographics = HashBasedTable.create();
            demographics.put(characterClass, level, count);
        } else {
            if (demographics.contains(characterClass, level)) {
                demographics.put(characterClass, level, count + demographics.get(characterClass, level));
            } else {
                demographics.put(characterClass, level, count);
            }
        }
    }

    public int getHighestLevel(CharacterClass characterClass) {
        Map<Integer, Integer> row = demographics.row(characterClass);

        int result = 0;
        if (row != null && !row.isEmpty()) {
            for (Integer key : row.keySet()) {
                if (key > result && row.containsKey(key) && row.get(key) > 0) {
                    result = key;
                }
            }
        }

        return result;
    }

    public int numberOf(CharacterClass characterClass) {
        Map<Integer, Integer> row = demographics.row(characterClass);

        int result = 0;
        if (row != null && !row.isEmpty()) {
            for (Integer key : row.keySet()) {
                if (row.containsKey(key)) {
                    result += row.get(key);
                }
            }
        }

        return result;
    }

    public int numberOf(CharacterClass characterClass, int level) {
        if (!demographics.contains(characterClass, level)) {
            return 0;
        } else {
            return demographics.get(characterClass, level);
        }
    }

    @Override
    public String toString() {
        return "CityDemographics [demographics=" + demographics + "]";
    }

}