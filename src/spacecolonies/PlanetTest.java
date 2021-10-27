// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)

package spacecolonies;

import student.TestCase;

/**
 * Tests methods in the Planet class.
 * 
 * @author aliparslan
 * @version 2021.04.07
 *
 */
public class PlanetTest extends TestCase {

    // Fields .............................................

    private Planet p;

    // Constructor ........................................

    /**
     * Empty constructor
     */
    public PlanetTest() {
        // intentionally left empty
    }

    // Methods ............................................


    /**
     * The code to be run before each test is performed
     */
    public void setUp() {
        p = new Planet("Mars", 1, 2, 3, 5);
    }


    /**
     * Tests all getters and setters including setName(), getName(),
     * getSkills(), getPopulation(), getPopulationSize(), getCapacity(),
     * getAvailability(), and isFull()
     */
    public void testGettersAndSetters() {
        assertEquals("Mars", p.getName());

        p.setName("Earth");
        assertEquals("Earth", p.getName());

        assertEquals(new Skillset(1, 2, 3), p.getSkills());

        assertEquals(0, p.getPopulationSize());
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        assertEquals(1, p.getPopulationSize());
        // need to do getPopulation()
        
        assertEquals(5, p.getCapacity());
        assertEquals(4, p.getAvailability());
        
        assertFalse(p.isFull());
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        assertTrue(p.isFull());
    }
    
    /**
     * tests addPerson()
     */
    public void testAddPerson() {
        assertTrue(p.addPerson(new Person("Alip", 1, 2, 3, "Mars")));
        assertFalse(p.addPerson(new Person("John", 0, 0, 0, "")));
        // ^ not qualified
        
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        p.addPerson(new Person("Alip", 1, 2, 3, "Mars"));
        assertFalse(p.addPerson(new Person("Alip", 1, 2, 3, "Mars")));
        // ^ not enough space
    }
    
    /**
     * tests isQualified()
     */
    public void testIsQualified() {
        assertFalse(p.isQualified(new Person("Alip", 1, 2, 2, "")));
        assertFalse(p.isQualified(new Person("Alip", 1, 1, 3, "")));
        assertFalse(p.isQualified(new Person("Alip", 0, 2, 3, "")));
        assertTrue(p.isQualified(new Person("Alip", 1, 2, 3, "")));
        assertTrue(p.isQualified(new Person("Alip", 5, 5, 5, "")));
    }
    
    /**
     * tests toString()
     */
    public void testToString() {
        assertEquals("Mars, population 0 (cap: 5), Requires: A >= 1, "
            + "M >= 2, T >= 3", p.toString());
    }
    
    /**
     * tests compareTo()
     */
    public void testCompareTo() {
        Planet a = new Planet("Mars", 1, 2, 3, 4);
        assertEquals(1, p.compareTo(a));
        assertEquals(-1, a.compareTo(p));
        
        Planet b = new Planet("Mars", 1, 2, 3, 5);
        b.addPerson(new Person("A", 1, 2, 3, ""));
        assertEquals(1, p.compareTo(b));
        assertEquals(-1, b.compareTo(p));
        
        Planet c = new Planet("Mars", 1, 2, 2, 5);
        assertEquals(1, p.compareTo(c));
        assertEquals(-1, c.compareTo(p));
        
        Planet d = new Planet("Earth", 3, 2, 1, 5);
        assertEquals(1, p.compareTo(d)); // because M comes after E
        assertEquals(-1, d.compareTo(p));
        
        Planet e = new Planet("Mars", 1, 2, 3, 5);
        assertEquals(0, p.compareTo(e));
        assertEquals(0, e.compareTo(p));
    }
    
    /**
     * tests equals()
     */
    public void testEquals() {
        assertTrue(p.equals(p));
        
        Object n = null;
        assertFalse(p.equals(n));
        
        Object o = new Object();
        assertFalse(p.equals(o));
        
        Planet a = new Planet("Mars", 1, 2, 3, 5);
        assertTrue(p.equals(a));
        p.addPerson(new Person("Jack", 1, 2, 3, "Mars"));
        a.addPerson(new Person("Jack", 1, 2, 3, "Mars"));
        assertTrue(p.equals(a));
        
        p.addPerson(new Person("John", 1, 2, 3, "Venus"));
        a.addPerson(new Person("John", 1, 2, 3, "Venus"));
        assertTrue(p.equals(a));
        assertTrue(a.equals(p));
    }
    
    
    

}
