package spacecolonies;

import java.util.Arrays;
import student.TestCase;

/**
 * Tests methods in the ColonyCalculator class
 * 
 * @author aliparslan
 * @version 2021.04.11
 *
 */
public class ColonyCalculatorTest extends TestCase {
    // Fields ...................................

    private ColonyCalculator c;

    // Constructor ..............................

    /**
     * Empty constructor
     */
    public ColonyCalculatorTest() {
        // intentionally left empty
    }

    // Methods ..................................


    /**
     * code to be run before each test
     */
    public void setUp() {
        ArrayQueue<Person> q = new ArrayQueue<Person>(1);
        q.enqueue(new Person("Michael Jordan", 5, 5, 5, "Planet1"));
        Planet[] p = new Planet[1];
        p[0] = new Planet("Planet1", 2, 2, 2, 2);
        c = new ColonyCalculator(q, p);
    }


    /**
     * tests getQueue() and getPlanets()
     */
    public void testGetters() {
        ArrayQueue<Person> a = new ArrayQueue<Person>(1);
        a.enqueue(new Person("Michael Jordan", 5, 5, 5, "Planet1"));
        assertTrue(c.getQueue().equals(a));

        Planet[] b = new Planet[1];
        b[0] = new Planet("Planet1", 2, 2, 2, 2);
        assertTrue(Arrays.equals(b, c.getPlanets()));
    }


    /**
     * 
     */
    public void testGetPlanetForPerson() {
        Planet[] p = new Planet[1];
        p[0] = new Planet("Planet1", 2, 2, 2, 2);
        assertEquals(p[0], c.getPlanetForPerson(new Person("Michael Jordan", 5,
            5, 5, "Planet1")));
    }

}
