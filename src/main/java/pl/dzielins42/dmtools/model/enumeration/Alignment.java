package pl.dzielins42.dmtools.model.enumeration;

public enum Alignment {

    LAWFUL_GOOD(Ethics.LAWFUL, Morals.GOOD),
    LAWFUL_NEUTRAL(Ethics.LAWFUL, Morals.NEUTRAL),
    LAWFUL_EVIL(Ethics.LAWFUL, Morals.EVIL),
    NEUTRAL_GOOD(Ethics.NEUTRAL, Morals.GOOD),
    NEUTRAL_NEUTRAL(Ethics.NEUTRAL, Morals.NEUTRAL),
    NEUTRAL_EVIL(Ethics.NEUTRAL, Morals.EVIL),
    CHAOTIC_GOOD(Ethics.CHAOTIC, Morals.GOOD),
    CHAOTIC_NEUTRAL(Ethics.CHAOTIC, Morals.NEUTRAL),
    CHAOTIC_EVIL(Ethics.CHAOTIC, Morals.EVIL);

    private Morals morals;
    private Ethics ethics;

    private Alignment(Ethics ethics, Morals morals) {
        this.morals = morals;
        this.ethics = ethics;
    }

    public Morals getMorals() {
        return morals;
    }

    public Ethics getEthics() {
        return ethics;
    }

    public static enum Morals {
        GOOD, NEUTRAL, EVIL;
    }

    public static enum Ethics {
        LAWFUL, NEUTRAL, CHAOTIC;
    }

}