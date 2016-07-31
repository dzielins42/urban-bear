package pl.dzielins42.dmtools.generator.name;

import pl.dzielins42.dmtools.generator.GeneratorOptions;

public class BasicNameGenerator implements NameGenerator {

    private static final String[] names = { "Adam", "Brandon", "Christopher", "David", "Eric", "Frank", "George", "Henry",
            "Ian", "Joe", "Kevin", "Liam", "Michael", "Nick", "Owen", "Paul", "Quin", "Rick", "Steve", "Terry", "Ulfred",
            "Vincent", "Walt", "Xavier", "Yuri", "Zachary", "Bob", "Ann", "Bambi", "Clare", "Diana", "Emma", "Faith",
            "Gabriela", "Hailey", "Ida", "Julia", "Kadia", "Lena", "Minnie", "Noelle", "Olivia", "Patricia", "Queenie", "Rae",
            "Sabine", "Talia", "Ursula", "Victoria", "Wanda", "Xena", "Yvonne", "Zara" };

    @Override
    public String generate(GeneratorOptions options) {
        String name;

        name = names[options.getRandom().nextInt(names.length)];

        return name;
    }

}