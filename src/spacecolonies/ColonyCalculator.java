package spacecolonies;

import java.util.Arrays;
import list.AList;

/**
 * This object handles all the major calculations and decision-making
 * for the program. It is in charge of handling accept and reject
 * instructions and checking that all requirements for a person are
 * met before they are able to be accepted to a planet. It works
 * together with SpaceWindow.
 * 
 * @author aliparslan
 * @version 2021.03.28
 *
 */
public class ColonyCalculator {

    // Fields ..................................................

    /**
     * Number of planets in the program
     */
    public static final int NUM_PLANETS = 3;

    /**
     * Minimum skill level required by a Planet/Person to be considered
     */
    public static final int MIN_SKILL_LEVEL = 1;

    /**
     * Maximum skill level required by a Planet/Person to be considered
     */
    public static final int MAX_SKILL_LEVEL = 5;

    private ArrayQueue<Person> applicantQueue;
    private AList<Person> rejectBus;
    private Planet[] planets;

    // Constructor .............................................

    /**
     * Creates a ColonyCalculator object with an ArrayQueue of Person objects
     * and an array of Planet objects
     * 
     * @param people
     *            ArrayQueue parameter
     * @param planets
     *            Planets array parameter
     * @throws IllegalArgumentException
     *             if either parameter is null
     */
    public ColonyCalculator(ArrayQueue<Person> people, Planet[] planets)
        throws IllegalArgumentException {
        if (people == null) {
            throw new IllegalArgumentException();
        }
        applicantQueue = people;
        this.planets = planets;
        rejectBus = new AList<Person>();
    }

    // Methods .................................................


    /**
     * Returns the queue holding applicants
     * 
     * @return the applicantQueue field
     */
    public ArrayQueue<Person> getQueue() {
        return applicantQueue;
    }


    /**
     * Returns the array of planets
     * 
     * @return the planets field
     */
    public Planet[] getPlanets() {
        return this.planets;
    }


    /**
     * Determines if a given planet has high enough
     * skills to be accepted to a given planet
     * 
     * @param planet
     *            the planet that is being tested
     * @param person
     *            the person tested to see if it will be accepted
     *            into the given planet
     * @return if the person is allowed to be on the planet
     */
    private boolean canAccept(Planet planet, Person person) {
        Skillset peS = person.getSkills();
        Skillset plS = planet.getSkills();
        return peS.getAgriculture() >= plS.getAgriculture() && peS
            .getMedicine() >= plS.getMedicine() && peS.getTechnology() >= plS
                .getTechnology();
    }


    /**
     * Returns a planet a person if they are eligible for their preferred
     * planet; if not, they are given the most available one they are
     * eligible for; if not eligible for any or all planets
     * are full, it will return null
     * 
     * @param nextPerson
     *            Person to consider
     * @return the planet that is right for them
     * 
     */
    public Planet getPlanetForPerson(Person nextPerson) {
        if (nextPerson == null) {
            return null;
        }
        String prefer = nextPerson.getPlanetPreference();
        if (this.getPlanetIndex(prefer) != -1 && this.canAccept(planets[this
            .getPlanetIndex(prefer)], nextPerson) &&
            !planets[this.getPlanetIndex(prefer)].isFull()) {
            return planets[this.getPlanetIndex(prefer)];
        }

        else if ((prefer == null || this.getPlanetIndex(prefer) == -1) && this
            .canAccept(this.getHighestCapacityPlanet(nextPerson), nextPerson)) {
            return this.getHighestCapacityPlanet(nextPerson);

        }

        return null;
    }


    /**
     * Returns the planet with the highest capacity that the Person can go to
     * 
     * @param person
     *            Person to consider
     * @return the planet with the highest capacity they are eligible for
     */
    private Planet getHighestCapacityPlanet(Person person) {
        Planet[] copy = Arrays.copyOf(planets, planets.length);
        Arrays.sort(copy, 0, copy.length);
        Planet p = null;
        for (int i = NUM_PLANETS - 1; i >= 0; i--) {
            p = copy[i];
            if (!p.isFull() && p.isQualified(person)) {
                return p;
            }
            p = null;
        }
        return p;
    }


    /**
     * Accepts the Person object into the Planet, if possible
     * 
     * @return if the person was successfully accepted to the planet
     */
    public boolean accept() {
        if (!this.getQueue().isEmpty()) {
            Person p = this.getQueue().getFront();
            Planet planet = this.getPlanetForPerson(p);

            if (planet != null) {
                planet.addPerson(p);
                this.getQueue().dequeue();
                return true;

            }
        }
        return false;
    }


    /**
     * Place the rejected person in line into the reject bus to
     * be put back in training school.
     */
    public void reject() {
        rejectBus.add(applicantQueue.dequeue());
    }


    /**
     * Tries to find a given planet name in the planets array,
     * returns its index or -1 if not found
     * 
     * @param planet
     *            name of planet to look for
     * @return the index of planets that the parameter was found
     */
    public int getPlanetIndex(String planet) {
        for (int i = 0; i < NUM_PLANETS; i++) {
            if (planets[i].getName().equals(planet)) {
                return i;
            }
            else if (planets[i].getName().equals("")) {
                return -1;
            }
        }
        return -1;
    }

}
