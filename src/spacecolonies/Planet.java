package spacecolonies;

import java.util.Arrays;

/**
 * These objects contain a string, for the planet’s name, three ints for
 * their minimum skill requirements (on a scale of 1 to 5), an array of
 * Person objects for current planet population, an int for storing the
 * current population size, and a final int for the maximum
 * allowed capacity of the planet.
 * 
 * @author aliparslan
 * @version 2021.03.28
 *
 */
public class Planet implements Comparable<Planet> {

    // Fields ....................................................

    private String name;
    private Skillset minSkills;
    private Person[] population;
    private int populationSize;
    private final int capacity;

    // Constructor ...............................................

    /**
     * Creates a new Planet object with five parameters representing
     * its name, the minimum skill requirements for agriculture, medicine,
     * and technology, and the maximum capacity for the planet
     * 
     * @param planetName
     *            name of the planet
     * @param planetAgri
     *            minimum agriculture skill required
     * @param planetMedi
     *            minimum planet skill required
     * @param planetTech
     *            minimum technology skill required
     * @param planetCap
     *            maximum capacity
     */
    public Planet(
        String planetName,
        int planetAgri,
        int planetMedi,
        int planetTech,
        int planetCap) {
        this.name = planetName;
        this.minSkills = new Skillset(planetAgri, planetMedi, planetTech);
        this.populationSize = 0;
        this.capacity = planetCap;
        this.population = new Person[this.capacity];
    }

    // Methods ...................................................


    /**
     * Changes the name of the planet
     * 
     * @param name
     *            new name of the planet
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Returns the name of the planet
     * 
     * @return name of the planet
     */
    public String getName() {
        return this.name;
    }


    /**
     * Returns the minimum skills required on the planet
     * 
     * @return the minSkills field
     */
    public Skillset getSkills() {
        return minSkills;
    }


    /**
     * Returns the population of the planet
     * 
     * @return the this.population field
     */
    public Person[] getPopulation() {
        return this.population;
    }


    /**
     * Returns the population size of the planet
     * 
     * @return the this.populationSize field
     */
    public int getPopulationSize() {
        return this.populationSize;
    }


    /**
     * The overall capacity of the planet
     * 
     * @return the this.capacity field
     */
    public int getCapacity() {
        return this.capacity;
    }


    /**
     * The remaining spaces left on the planet
     * 
     * @return the difference between the capacity and populationSize fields
     */
    public int getAvailability() {
        return capacity - populationSize;
    }


    /**
     * If the planet has any more availability
     * 
     * @return if the capacity and populationSize fields are equal
     */
    public boolean isFull() {
        return capacity == populationSize;
    }


    /**
     * Determines if a new Person is able to be added to a planet.
     * If they are qualified and the planet has room, they will
     * be added and the method will return true
     * 
     * @param newbie
     *            the Person object to be considered
     * @return if the parameter was added successfully
     */
    public boolean addPerson(Person newbie) {
        if (!this.isFull() && this.isQualified(newbie)) {
            population[populationSize] = newbie;
            populationSize++;
            return true;
        }
        return false;
    }


    /**
     * Checks if a Person object has high enough skill attributes
     * to inhabit the current Planet
     * 
     * @param p
     *            Person to check if they are qualified
     * @return if the person is qualified to live on the planet
     */
    public boolean isQualified(Person p) {
        return p.getSkills().getAgriculture() >= this.minSkills.getAgriculture()
            && p.getSkills().getMedicine() >= this.minSkills.getMedicine() && p
                .getSkills().getTechnology() >= this.minSkills.getTechnology();
    }


    /**
     * Returns the Planet as a String representation.
     * For example, "Mars, population 5 (cap: 10), Requires: A >= 3,
     * M >= 2, T >= 1"
     * 
     * @return a string representation of a Planet object
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.name);
        s.append(", population ");
        s.append(this.populationSize);
        s.append(" (cap: ");
        s.append(this.capacity);
        s.append("), Requires: A >= ");
        s.append(this.minSkills.getAgriculture());
        s.append(", M >= ");
        s.append(this.minSkills.getMedicine());
        s.append(", T >= ");
        s.append(this.minSkills.getTechnology());
        return s.toString();
    }


    /**
     * In order, two planets will be ordered based on capacity, availability,
     * Skillset, and name.
     * 
     * @param p
     *            Planet object to be compared to
     * @return -1, 0, or 1 based on depending on the inequality of the Planets
     */
    @Override
    public int compareTo(Planet p) {
        if (this.capacity > p.capacity) {
            return 1;
        }
        else if (this.capacity < p.capacity) {
            return -1;
        }
        else {
            if (this.getAvailability() > p.getAvailability()) {
                return 1;
            }
            else if (this.getAvailability() < p.getAvailability()) {
                return -1;
            }
            else {
                if (this.minSkills.compareTo(p.minSkills) == 0) {
                    if (this.name.compareTo(p.name) < 0) {
                        return -1;
                    }
                    else if (this.name.compareTo(p.name) > 0) {
                        return 1;
                    }
                    else {
                        return 0;
                    }
                }
                else {
                    return this.minSkills.compareTo(p.minSkills);
                }
            }
        }
    }


    /**
     * Two planets are equal if all 5 their input fields
     * are equal and they have the the same population.
     * 
     * @param obj
     *            the planet to be compared to
     * @return if the two Planet objects are equal
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
            Planet p = (Planet)obj;
            return this.getName().equals(p.getName()) && this.getSkills()
                .equals(p.getSkills()) && this.getCapacity() == p.getCapacity()
                && Arrays.equals(this.getPopulation(), p.getPopulation());
        }
        return false;
    }

}
