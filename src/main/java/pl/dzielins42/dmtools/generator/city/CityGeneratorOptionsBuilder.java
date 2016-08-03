package pl.dzielins42.dmtools.generator.city;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.LinkedHashMap;

import com.google.common.collect.ArrayTable;
import com.google.common.collect.Table;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

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
    private EnumMap<Ward.Type, ProbabilityDistributionTable<WardBuilding.TypeStyleTuple>> structureTypesProbabilities;
    private EnumMap<City.Type, Double> cityTypesGpLimits;
    private EnumMap<City.Type, Double> cityTypesUnabsorbedInfluencePoints;
    private EnumMap<City.Type, Double> cityTypesMagicalResources;
    private Table<City.Type, CharacterClass, IntProbabilityDistributionTable> cityDemographics;

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

        return cgo;
    }

    public void setRandom(RandomGenerator random) {
        this.random = random;
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
            Type type = new TypeToken<ProbabilityDistributionTable<City.Type>>() {
            }.getType();
            pd = gson.fromJson(fr, type);
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
            LinkedHashMap<City.Type, ProbabilityDistributionTable<Ward.Type>> lhm;
            Type type = new TypeToken<LinkedHashMap<City.Type, ProbabilityDistributionTable<Ward.Type>>>() {
            }.getType();
            lhm = gson.fromJson(fr, type);
            if (lhm != null) {
                em = new EnumMap<City.Type, ProbabilityDistributionTable<Ward.Type>>(City.Type.class);
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
            IntMinMaxValues[][] a;
            a = gson.fromJson(fr, IntMinMaxValues[][].class);
            if (a != null) {
                t = ArrayTable.create(Arrays.asList(Ward.Type.values()), Arrays.asList(City.Type.values()));
                for (Ward.Type wt : Ward.Type.values()) {
                    for (City.Type ct : City.Type.values()) {
                        t.put(wt, ct, a[wt.ordinal()][ct.ordinal()]);
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
            EnumMap<Ward.Type, ProbabilityDistributionTable<WardBuilding.TypeStyleTuple>> structureTypesProbabilities) {
        this.structureTypesProbabilities = structureTypesProbabilities;
    }

    public void loadStructureTypesProbabilities(File file) {
        if (file == null) {
            throw new IllegalArgumentException();
        }

        FileReader fr = null;
        EnumMap<Ward.Type, ProbabilityDistributionTable<WardBuilding.TypeStyleTuple>> em = null;
        try {
            Gson gson = new Gson();
            fr = new FileReader(file);
            LinkedHashMap<Ward.Type, ProbabilityDistributionTable<WardBuilding.TypeStyleTuple>> lhm;
            Type type = new TypeToken<LinkedHashMap<Ward.Type, ProbabilityDistributionTable<WardBuilding.TypeStyleTuple>>>() {
            }.getType();
            lhm = gson.fromJson(fr, type);
            if (lhm != null) {
                em = new EnumMap<Ward.Type, ProbabilityDistributionTable<WardBuilding.TypeStyleTuple>>(Ward.Type.class);
                for (Ward.Type ct : Ward.Type.values()) {
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
            IntProbabilityDistributionTable[][] a;
            a = gson.fromJson(fr, IntProbabilityDistributionTable[][].class);
            if (a != null) {
                t = ArrayTable.create(Arrays.asList(City.Type.values()), Arrays.asList(CharacterClass.values()));
                for (City.Type ct : City.Type.values()) {
                    for (CharacterClass cc : CharacterClass.values()) {
                        t.put(ct, cc, a[ct.ordinal()][cc.ordinal()]);
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