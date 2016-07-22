package pl.dzielins42.dmtools.model.religion;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Joiner;

import pl.dzielins42.dmtools.model.Alignment;
import pl.dzielins42.dmtools.model.Gender;

public class Deity {

    private String name;
    private Alignment alignment;
    private Gender gender;
    /**
     * Determines how much power the deity has.
     */
    private byte divineRank;
    private List<Domain> domains;

    public Deity(String name, Alignment alignment, Gender gender, int divineRank, List<Domain> domains) {
        super();
        this.name = name;
        this.alignment = alignment;
        this.gender = gender;
        this.divineRank = (byte) divineRank;
        this.domains = domains;
    }

    public String getName() {
        return name;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public Gender getGender() {
        return gender;
    }

    public byte getDivineRank() {
        return divineRank;
    }

    public List<Domain> getDomains() {
        return domains;
    }

    public String toPrettyString() {
        StringBuilder sb = new StringBuilder();

        sb.append(name);
        sb.append(", ");
        sb.append(alignmentToPrettyString());
        sb.append(" ");
        switch (gender) {
        case FEMALE:
            sb.append("goddess");
            break;
        case MALE:
            sb.append("god");
            break;
        case OTHER:
        default:
            sb.append("deity");
            break;
        }
        if (domains != null && !domains.isEmpty()) {
            sb.append(" of ");
            sb.append(domainsToPrettyString());
        }

        return sb.toString();
    }

    private String domainsToPrettyString() {
        if (domains == null || domains.isEmpty()) {
            return null;
        } else if (domains.size() == 1) {
            return String.valueOf(domains.get(0)).toLowerCase();
        } else {
            List<Domain> temp = new ArrayList<Domain>(domains);
            return Joiner.on(", ").join(temp.subList(0, temp.size() - 1)).concat(" and ")
                    .concat(String.valueOf(temp.get(temp.size() - 1))).toLowerCase();
        }
    }

    private String alignmentToPrettyString() {
        switch (alignment) {
        case CHAOTIC_EVIL:
            return "chaotic evil";
        case CHAOTIC_GOOD:
            return "chaotic good";
        case CHAOTIC_NEUTRAL:
            return "chaotic neutral";
        case LAWFUL_EVIL:
            return "lawful evil";
        case LAWFUL_GOOD:
            return "lawful good";
        case LAWFUL_NEUTRAL:
            return "lawful neutral";
        case NEUTRAL_EVIL:
            return "neutral evli";
        case NEUTRAL_GOOD:
            return "neutral good";
        case NEUTRAL_NEUTRAL:
        default:
            return "neutral";
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((alignment == null) ? 0 : alignment.hashCode());
        result = prime * result + divineRank;
        result = prime * result + ((domains == null) ? 0 : domains.hashCode());
        result = prime * result + ((gender == null) ? 0 : gender.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Deity other = (Deity) obj;
        if (alignment != other.alignment)
            return false;
        if (divineRank != other.divineRank)
            return false;
        if (domains == null) {
            if (other.domains != null)
                return false;
        } else if (!domains.equals(other.domains))
            return false;
        if (gender != other.gender)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Deity [name=" + name + ", alignment=" + alignment + ", gender=" + gender + ", divineRank=" + divineRank
                + ", domains=" + domains + "]";
    }

}