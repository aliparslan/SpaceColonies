package spacecolonies;

import student.TestCase;

/**
 * Tests methods in the Person class.
 * 
 * @author aliparslan
 * @version 2021.04.07
 *
 */
public class PersonTest extends TestCase {

    // Fields ........................................

    private Person p;

    // Constructor ...................................

    /**
     * Empty constructor
     */
    public PersonTest() {
        // Intentionally left empty
    }

    // Methods .......................................


    /**
     * The code to be run before each test is performed
     */
    public void setUp() {
        p = new Person("Alip", 1, 2, 3, "Mars");
    }


    /**
     * Tests the getter methods getName(), getSkills(), and
     * getPlanetPreference()
     */
    public void testGetters() {
        assertEquals("Alip", p.getName());
        assertEquals("Mars", p.getPlanetPreference());
        assertEquals(new Skillset(1, 2, 3), p.getSkills());
    }


    /**
     * Tests toString()
     */
    public void testToString() {
        assertEquals("Alip A:1 M:2 T:3 Wants: Mars", p.toString());

        Person a = new Person("Alex", 1, 2, 3, "");
        assertEquals("No-Planet Alex A:1 M:2 T:3", a.toString());
    }


    /**
     * Tests equals()
     */
    public void testEquals() {
        assertTrue(p.equals(p));

        Object n = null;
        assertFalse(p.equals(n));

        Object o = new Object();
        assertFalse(p.equals(o));

        Person a = new Person("Alip", 0, 1, 2, "Mars");
        assertFalse(p.equals(a));

        Person b = new Person("John", 1, 2, 3, "Mars");
        assertFalse(p.equals(b));

        Person c = new Person("Alip", 1, 2, 3, "Venus");
        assertFalse(p.equals(c));

        Person d = new Person("Alip", 1, 2, 3, "Mars");
        assertTrue(p.equals(d));
    }
}
