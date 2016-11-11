package pl.dzielins42.dmtools.generator.name;

public class SpaceJoinNameGenerator extends JoinNameGenerator {

    private static final String SPACE = " ";

    public SpaceJoinNameGenerator(NameGenerator first, NameGenerator second) {
        super(first, second, SPACE);
    }

}