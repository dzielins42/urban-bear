package pl.dzielins42.dmtools.generator.city;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.javatuples.Pair;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pl.dzielins42.dmtools.model.CharacterClass;
import pl.dzielins42.dmtools.model.city.City;
import pl.dzielins42.dmtools.model.city.Ward;
import pl.dzielins42.dmtools.model.city.WardBuilding;
import pl.dzielins42.dmtools.util.ProbabilityDistributionTable;
import pl.dzielins42.dmtools.util.RandomGenerator;
import pl.dzielins42.dmtools.util.data.IntMinMaxValues;
import pl.dzielins42.dmtools.util.data.IntProbabilityDistributionTable;

public class CityGeneratorOptionsBuilder {

    private RandomGenerator random;
    private ProbabilityDistributionTable<City.Type> cityTypesProbabilities;
    private EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>> wardTypesProbabilities;
    private Table<Ward.Type, City.Type, IntMinMaxValues> wardStructuresDistributions;
    private EnumMap<Ward.Type, ProbabilityDistributionTable<Pair<WardBuilding.Style, WardBuilding.Type>>> structureTypesProbabilities;
    private EnumMap<City.Type, Double> cityTypesGpLimits;
    private EnumMap<City.Type, Double> cityTypesUnabsorbedInfluencePoints;
    private EnumMap<City.Type, Double> cityTypesMagicalResources;
    private Table<City.Type, CharacterClass, IntProbabilityDistributionTable> cityDemographics;
    private EnumMap<City.Type, Integer> cityDemographicsMaxLevelRollsNumber;
    private EnumMap<City.Type,IntMinMaxValues> powerCenterNumber;

    public CityGeneratorOptions build() {
        // Validate
        if (random == null || cityTypesProbabilities == null || wardTypesProbabilities == null
                || wardStructuresDistributions == null || structureTypesProbabilities == null) {
            throw new IllegalStateException();
        }

        CityGeneratorOptions cgo = new CityGeneratorOptions(random);

        cgo.setCityTypesProbabilities(cityTypesProbabilities);
        cgo.setWardTypesProbabilities(wardTypesProbabilities);
        cgo.setWardStructuresDistributions(wardStructuresDistributions);
        cgo.setStructureTypesProbabilities(structureTypesProbabilities);
        cgo.setCityTypesGpLimits(cityTypesGpLimits);
        cgo.setCityTypesUnabsorbedInfluencePoints(cityTypesUnabsorbedInfluencePoints);
        cgo.setCityTypesMagicalResources(cityTypesMagicalResources);
        cgo.setCityDemographics(cityDemographics);
        cgo.setCityDemographicsMaxLevelRollsNumber(cityDemographicsMaxLevelRollsNumber);
        cgo.setPowerCenterNumber(powerCenterNumber);

        return cgo;
    }

    public void setRandom(RandomGenerator random) {
        this.random = random;
    }

    public void setPowerCenterNumber(EnumMap<City.Type, IntMinMaxValues> powerCenterNumber) {
        this.powerCenterNumber = powerCenterNumber;
    }
    
    public void loadPowerCenterNumber(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<City.Type, IntMinMaxValues> em = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            Map<City.Type,IntMinMaxValues> m;
            Type type = new TypeToken<Map<City.Type,IntMinMaxValues>>() {
                private static final long serialVersionUID = -6756787428428024271L;
            }.getType();
            m = gson.fromJson(fr, type);
            if (m != null && !m.isEmpty()) {
                em = new EnumMap<City.Type, IntMinMaxValues>(City.Type.class);
                for (City.Type ct : City.Type.values()) {
                    em.put(ct, m.get(ct));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (em != null) {
            setPowerCenterNumber(em);
        }
    }

    public void setCityDemographicsMaxLevelRollsNumber(EnumMap<City.Type, Integer> cityDemographicsMaxLevelRollsNumber) {
        this.cityDemographicsMaxLevelRollsNumber = cityDemographicsMaxLevelRollsNumber;
    }

    public void loadCityDemographicsMaxLevelRollsNumber(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<City.Type, Integer> em = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            Type type = new TypeToken<Map<City.Type, Integer>>() {
                private static final long serialVersionUID = -1091771428115745077L;
            }.getType();
            Map<City.Type, Integer> m = gson.fromJson(fr, type);
            if (m != null) {
                em = new EnumMap<City.Type, Integer>(City.Type.class);
                for (City.Type ct : City.Type.values()) {
                    em.put(ct, m.get(ct));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (em != null) {
            setCityDemographicsMaxLevelRollsNumber(em);
        }
    }

    public void setCityTypesProbabilities(ProbabilityDistributionTable<City.Type> cityTypesProbabilities) {
        this.cityTypesProbabilities = cityTypesProbabilities;
    }

    public void loadCityTypesProbabilities(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        ProbabilityDistributionTable<City.Type> pd = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            Type type = new TypeToken<Map<City.Type, Double>>() {
                private static final long serialVersionUID = -6756787428428024271L;
            }.getType();
            Map<City.Type, Double> m = gson.fromJson(fr, type);
            if (m != null && !m.isEmpty()) {
                pd = new ProbabilityDistributionTable<City.Type>(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (pd != null) {
            setCityTypesProbabilities(pd);
        }
    }

    public void setWardTypesProbabilities(EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>> wardTypesProbabilities) {
        this.wardTypesProbabilities = wardTypesProbabilities;
    }

    public void loadWardTypesProbabilities(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>> em = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            Type type = new TypeToken<Map<City.Type, Map<Ward.Type, Double>>>() {
                private static final long serialVersionUID = -1091771428115745077L;
            }.getType();
            Map<City.Type, Map<Ward.Type, Double>> m;
            m = gson.fromJson(fr, type);
            if (m != null) {
                em = new EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>>(City.Type.class);
                for (City.Type ct : City.Type.values()) {
                    em.put(ct, new ProbabilityDistributionTable<Ward.Type>(m.get(ct)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (em != null) {
            setWardTypesProbabilities(em);
        }
    }

    public void setWardStructuresDistributions(Table<Ward.Type, City.Type, IntMinMaxValues> wardStructuresDistributions) {
        this.wardStructuresDistributions = wardStructuresDistributions;
    }

    public void loadWardStructuresDistributions(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        Table<Ward.Type, City.Type, IntMinMaxValues> t = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            Map<City.Type, Map<Ward.Type, IntMinMaxValues>> m;
            Type type = new TypeToken<Map<City.Type, Map<Ward.Type, IntMinMaxValues>>>() {
                private static final long serialVersionUID = -9132635200807902047L;
            }.getType();
            m = gson.fromJson(fr, type);
            if (m != null && !m.isEmpty()) {
                t = ArrayTable.create(Arrays.asList(Ward.Type.values()), Arrays.asList(City.Type.values()));
                for (Ward.Type wt : Ward.Type.values()) {
                    for (City.Type ct : City.Type.values()) {
                        t.put(wt, ct, m.get(ct).get(wt));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (t != null) {
            setWardStructuresDistributions(t);
        }
    }

    public void setStructureTypesProbabilities(
            EnumMap<Ward.Type, ProbabilityDistributionTable<Pair<WardBuilding.Style, WardBuilding.Type>>> structureTypesProbabilities) {
        this.structureTypesProbabilities = structureTypesProbabilities;
    }

    public void loadStructureTypesProbabilities(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<Ward.Type, ProbabilityDistributionTable<Pair<WardBuilding.Style, WardBuilding.Type>>> em = null;
        try {
            Gson gson = new GsonBuilder().create();
            fr = new FileReader(file);
            Type type = new TypeToken<Map<Ward.Type, Map<WardBuilding.Type, Map<WardBuilding.Style, Double>>>>() {
                private static final long serialVersionUID = 8342343019155386549L;
            }.getType();
            Map<Ward.Type, Map<WardBuilding.Type, Map<WardBuilding.Style, Double>>> m = gson.fromJson(fr, type);
            if (m != null && !m.isEmpty()) {
                List<Pair<WardBuilding.Style, WardBuilding.Type>> elements = new ArrayList<Pair<WardBuilding.Style, WardBuilding.Type>>(
                        WardBuilding.Style.values().length * WardBuilding.Type.values().length);
                for (WardBuilding.Style wbs : WardBuilding.Style.values()) {
                    for (WardBuilding.Type wbt : WardBuilding.Type.values()) {
                        elements.add(new Pair<WardBuilding.Style, WardBuilding.Type>(wbs, wbt));
                    }
                }
                List<Double> values;
                em = new EnumMap<Ward.Type, ProbabilityDistributionTable<Pair<WardBuilding.Style, WardBuilding.Type>>>(
                        Ward.Type.class);
                for (Ward.Type wt : Ward.Type.values()) {
                    values = new ArrayList<Double>(WardBuilding.Style.values().length * WardBuilding.Type.values().length);
                    for (WardBuilding.Style wbs : WardBuilding.Style.values()) {
                        for (WardBuilding.Type wbt : WardBuilding.Type.values()) {
                            values.add(m.get(wt).get(wbt).get(wbs));
                        }
                    }
                    em.put(wt, new ProbabilityDistributionTable<>(elements, values));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (em != null) {
            setStructureTypesProbabilities(em);
        }
    }

    public void setCityTypesGpLimits(EnumMap<City.Type, Double> cityTypesGpLimits) {
        this.cityTypesGpLimits = cityTypesGpLimits;
    }

    public void loadCityTypesGpLimits(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<City.Type, Double> em = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            LinkedHashMap<City.Type, Double> lhm;
            Type type = new TypeToken<LinkedHashMap<City.Type, Double>>() {
                private static final long serialVersionUID = 7271405298010198405L;
            }.getType();
            lhm = gson.fromJson(fr, type);
            if (lhm != null) {
                em = new EnumMap<City.Type, Double>(City.Type.class);
                for (City.Type ct : City.Type.values()) {
                    em.put(ct, lhm.get(ct));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (em != null) {
            setCityTypesGpLimits(em);
        }
    }

    public void setCityTypesUnabsorbedInfluencePoints(EnumMap<City.Type, Double> cityTypesUnabsorbedInfluencePoints) {
        this.cityTypesUnabsorbedInfluencePoints = cityTypesUnabsorbedInfluencePoints;
    }

    public void loadCityTypesUnabsorbedInfluencePoints(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<City.Type, Double> em = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            LinkedHashMap<City.Type, Double> lhm;
            Type type = new TypeToken<LinkedHashMap<City.Type, Double>>() {
                private static final long serialVersionUID = -4363663375389495375L;
            }.getType();
            lhm = gson.fromJson(fr, type);
            if (lhm != null) {
                em = new EnumMap<City.Type, Double>(City.Type.class);
                for (City.Type ct : City.Type.values()) {
                    em.put(ct, lhm.get(ct));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (em != null) {
            setCityTypesUnabsorbedInfluencePoints(em);
        }
    }

    public void setCityTypesMagicalResources(EnumMap<City.Type, Double> cityTypesMagicalResources) {
        this.cityTypesMagicalResources = cityTypesMagicalResources;
    }

    public void loadCityTypesMagicalResources(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<City.Type, Double> em = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            LinkedHashMap<City.Type, Double> lhm;
            Type type = new TypeToken<LinkedHashMap<City.Type, Double>>() {
                private static final long serialVersionUID = -3121484466580675833L;
            }.getType();
            lhm = gson.fromJson(fr, type);
            if (lhm != null) {
                em = new EnumMap<City.Type, Double>(City.Type.class);
                for (City.Type ct : City.Type.values()) {
                    em.put(ct, lhm.get(ct));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (em != null) {
            setCityTypesMagicalResources(em);
        }
    }

    public void setCityDemographics(Table<City.Type, CharacterClass, IntProbabilityDistributionTable> cityDemographics) {
        this.cityDemographics = cityDemographics;
    }

    public void loadCityDemographics(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        Table<City.Type, CharacterClass, IntProbabilityDistributionTable> t = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            Map<City.Type, Map<CharacterClass, Map<Integer, Double>>> m;
            Type type = new TypeToken<Map<City.Type, Map<CharacterClass, Map<Integer, Double>>>>() {
                private static final long serialVersionUID = 8630640637108522310L;
            }.getType();
            m = gson.fromJson(fr, type);
            if (m != null && !m.isEmpty()) {
                t = ArrayTable.create(Arrays.asList(City.Type.values()), Arrays.asList(CharacterClass.values()));
                for (City.Type ct : City.Type.values()) {
                    for (CharacterClass cc : CharacterClass.values()) {
                        t.put(ct, cc, new IntProbabilityDistributionTable(m.get(ct).get(cc)));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if (t != null) {
            setCityDemographics(t);
        }
    }

}