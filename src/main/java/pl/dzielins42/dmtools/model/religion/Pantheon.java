package pl.dzielins42.dmtools.model.religion;

import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

public class Pantheon {

    private String name;
    private List<Deity> deities;

    public Pantheon(String name, List<Deity> deities) {
        super();

        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException();
        }

        this.name = name;
        this.deities = deities;
    }

    public String getName() {
        return name;
    }

    public List<Deity> getDeities() {
        return deities;
    }

    public String toPrettyString() {
        if (deities == null || deities.isEmpty()) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            String[] deitiesPrettyString = new String[deities.size()];
            for (int i = 0; i < deities.size(); i++) {
                deitiesPrettyString[i] = deities.get(i).toPrettyString();
            }
            sb.append(name).append(":").append(System.lineSeparator())
                    .append(Joiner.on(System.lineSeparator()).join(deitiesPrettyString));
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        return "Pantheon [name=" + name + ", deities=" + deities + "]";
    }

}