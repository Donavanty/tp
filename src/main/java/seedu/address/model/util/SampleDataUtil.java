package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Address;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Rating;
import seedu.address.model.person.Review;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("KFC"), new Rating("3"), new Review("too oily"),
                    new Address("3155 Commonwealth Ave W, #B1-32/33, Singapore 129588"),
                    getTagSet("FastFood")),
            new Person(new Name("Frontier"), new Rating("4"), new Review("cheap good food!"),
                    new Address("12 Science Drive 2, Singapore 117549"),
                    getTagSet("Others")),
            new Person(new Name("Mcdonald"), new Rating("1"), new Review("Food sucks, not properly cooked."),
                new Address("Blk 30 Geylang Street 29, #06-40"), getTagSet("FastFood")),
            new Person(new Name("Ameens"), new Rating("5"), new Review("Cheese fries is lit!!!"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), getTagSet("Western")),
            new Person(new Name("Techo Edge"), new Rating("5"),
                new Review("Food is cheap and good! Especially the mixed rice stall."),
                new Address("2 Engineering Drive 4, Singapore 117584"), getTagSet("FastFood")),
            new Person(new Name("PGP Canteen"), new Rating("4"),
                new Review("Good food. Place very crowded during lunch"),
                new Address("27 Prince George's Park, Singapore 118425"), getTagSet("Western", "Indian")),
            new Person(new Name("Deck"), new Rating("4"),
                new Review("Mala the best mannn!"),
                new Address("NUS School of Computing, COM1, 13, Computing Dr, 117417"),
                getTagSet("Western"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
