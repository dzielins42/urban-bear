package pl.dzielins42.dmtools.generator.city;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import pl.dzielins42.dmtools.model.CharacterClass;
import pl.dzielins42.dmtools.model.city.City;
import pl.dzielins42.dmtools.model.city.CityDemographics;
import pl.dzielins42.dmtools.util.data.IntProbabilityDistributionTable;

public class CityDemographicsGenerator {

    public CityDemographics generate(City.Type cityType, int population, CityGeneratorOptions options) {
        if (options == null || population <= 0 || cityType == null || options.getCityDemographics() == null) {
            throw new IllegalArgumentException();
        }

        Map<CharacterClass, IntProbabilityDistributionTable> demographicsStructure = options.getCityDemographics()
                .row(cityType);
        if (demographicsStructure == null || demographicsStructure.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Table<CharacterClass, Integer, Integer> demographics = HashBasedTable.create();

        int remainingPopulation = population;

        // For each class generate highest level NPCs
        int maxLvl = 0;
        int numberOfRolls = options.getCityDemographicsMaxLevelRollsNumber().get(cityType);
        for (int i = 0; i < numberOfRolls; i++) {
            for (CharacterClass cc : CharacterClass.values()) {
                if (remainingPopulation <= 0) {
                    break;
                }
                if (demographicsStructure.containsKey(cc)) {
                    int lvl = demographicsStructure.get(cc).getRandom(options.getRandom());
                    if (lvl > 0) {
                        addToTable(demographics, cc, lvl, 1);
                        remainingPopulation--;
                        maxLvl = (lvl > maxLvl) ? lvl : maxLvl;
                    }
                }
            }
        }
        // Generate lower level NPCs
        for (int lvl = maxLvl; lvl > 1; lvl--) {
            Map<CharacterClass, Integer> levelColumn = demographics.column(lvl);
            if (levelColumn != null && !levelColumn.isEmpty()) {
                for (CharacterClass cc : levelColumn.keySet()) {
                    if (levelColumn.get(cc) != null) {
                        for (int i = 0; (i < levelColumn.get(cc) * 2) && (remainingPopulation > 0); i++) {
                            int newLvl;
                            // Use random method, divide or reduce higher level
                            // by 2
                            if (options.nextBoolean()) {
                                newLvl = Math.floorDiv(lvl, 2);
                            } else {
                                newLvl = lvl - 2;
                            }
                            // Truncate to minimum 1
                            if (newLvl <= 0) {
                                newLvl = 1;
                            }
                            // Ignore 1st level NPCs generation for NPC classes
                            if (newLvl == 1 && (cc == CharacterClass.ADEPT || cc == CharacterClass.ARISTOCRAT
                                    || cc == CharacterClass.COMMONER || cc == CharacterClass.EXPERT
                                    || cc == CharacterClass.WARRIOR)) {
                                continue;
                            }
                            addToTable(demographics, cc, newLvl, 1);
                            remainingPopulation--;
                        }
                    }
                }
            }
        }
        // Generate 1st level NPCs with NPC classes
        if (remainingPopulation > 0) {
            int commoners = (int) (0.91d * remainingPopulation);
            int warriors = (int) (0.05d * remainingPopulation);
            int experts = (int) (0.03d * remainingPopulation);
            int aristocrats = (int) Math.ceil(0.5d * (remainingPopulation - commoners - warriors - experts));
            int adepts = remainingPopulation - commoners - warriors - experts - aristocrats;
            addToTable(demographics, CharacterClass.COMMONER, 1, commoners);
            addToTable(demographics, CharacterClass.WARRIOR, 1, warriors);
            addToTable(demographics, CharacterClass.EXPERT, 1, experts);
            addToTable(demographics, CharacterClass.ARISTOCRAT, 1, aristocrats);
            addToTable(demographics, CharacterClass.ADEPT, 1, adepts);
            // If anything is left or assigned to much, compensate using 1st
            // level commoners
            remainingPopulation -= (commoners + warriors + experts + aristocrats + adepts);
            if (remainingPopulation != 0) {
                addToTable(demographics, CharacterClass.COMMONER, 1, 1);
                remainingPopulation = 0;
            }
        }

        return new CityDemographics(demographics);
    }

    private void addToTable(Table<CharacterClass, Integer, Integer> table, CharacterClass characterClass, int level,
            int count) {
        if (table.contains(characterClass, level)) {
            table.put(characterClass, level, count + table.get(characterClass, level));
        } else {
            table.put(characterClass, level, count);
        }
    }

}