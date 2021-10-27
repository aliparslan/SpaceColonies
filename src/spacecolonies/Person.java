// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)
package spacecolonies;

/**
 * These objects contain a string, for a person’s name, a Skillset object,
 * and a String representation of their planet preference.
 * 
 * @author aliparslan
 * @version 2021.03.28
 *
 */
public class Person {

    // Fields .....................................................

    private String name;
    private Skillset skills;
    private String planetPreference;

    // Constructor ................................................

    /**
     * Creates a new Person object with five parameters representing
     * their name, Skillset, and preference
     * 
     * @param name
     *            name of the person
     * @param agri
     *            agriculture skill value
     * @param medi
     *            medicine skill value
     * @param tech
     *            technology skill value
     * @param planet
     *            name of preferred planet
     */
    public Person(String name, int agri, int medi, int tech, String planet) {
        this.name = name;
        this.planetPreference = planet;
        this.skills = new Skillset(agri, medi, tech);
    }

    // Methods ....................................................


    /**
     * Returns the Person's name
     * 
     * @return name field
     */
    public String getName() {
        return this.name;
    }


    /**
     * Returns the Person's skillset
     * 
     * @return skills field
     */
    public Skillset getSkills() {
        return this.skills;
    }


    /**
     * Returns the Person's preferred planet
     * 
     * @return planetPreference field
     */
    public String getPlanetPreference() {
        return this.planetPreference;
    }


    /**
     * Returns the person as a String representation.
     * For example, "Jane Doe A:3 M:2 T:1 Wants: Mars"
     * or "No-Planet Jane Doe A:3 M:2 T:1"
     * 
     * @return a string representation of the Person
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.name);
        s.append(" ");
        s.append(this.skills.toString());
        if (this.planetPreference.length() > 0) {
            s.append(" Wants: ");
            s.append(this.planetPreference);
        }
        else {
            s.insert(0, "No-Planet ");
        }
        return s.toString();
    }


    /**
     * Two Person objects are equal if they have the same name,
     * skillset, and planet preference values
     * 
     * @return if the two Person objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            Person p = (Person)obj;
            return this.getName() == p.getName() && this.getSkills().equals(p
                .getSkills()) && this.getPlanetPreference() == p
                    .getPlanetPreference();
        }
        return false;
    }
}
