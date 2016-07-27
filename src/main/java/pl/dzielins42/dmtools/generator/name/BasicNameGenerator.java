package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;
import pl.dzielins42.dmtools.model.Gender;

public class BasicNameGenerator implements NameGenerator {

    private static final String[] maleNames = { "Adam", "Brandon", "Christopher", "David", "Eric", "Frank", "George", "Henry",
            "Ian", "Joe", "Kevin", "Liam", "Michael", "Nick", "Owen", "Paul", "Quin", "Rick", "Steve", "Terry", "Ulfred",
            "Vincent", "Walt", "Xavier", "Yuri", "Zachary", "Bob" };
    private static final String[] femaleNames = { "Ann", "Bambi", "Clare", "Diana", "Emma", "Faith", "Gabriela", "Hailey",
            "Ida", "Julia", "Kadia", "Lena", "Minnie", "Noelle", "Olivia", "Patricia", "Queenie", "Rae", "Sabine", "Talia",
            "Ursula", "Victoria", "Wanda", "Xena", "Yvonne", "Zara" };

    @Override
    public String generate(Gender gender, GeneratorOptions options) {
        String name;

        switch (gender) {
        case FEMALE:
            name = femaleNames[options.getRandom().nextInt(femaleNames.length)];
            break;
        case MALE:
            name = maleNames[options.getRandom().nextInt(maleNames.length)];
            break;
        case OTHER:
        default:
            if (options.getRandom().nextBoolean()) {
                name = maleNames[options.getRandom().nextInt(maleNames.length)];
            } else {
                name = femaleNames[options.getRandom().nextInt(femaleNames.length)];
            }
            name += "?";
            break;
        }

        return name;
    }

}
