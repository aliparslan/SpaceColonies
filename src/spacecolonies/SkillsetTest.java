package spacecolonies;
import student.TestCase;

/**
 * Tests methods in the Skillset class.
 * @author aliparslan
 * @version 2021.04.07
 *
 */
public class SkillsetTest extends TestCase {
    
    // Fields ................................................
    
    private Skillset s;
    
    // Constructor ...........................................
    
    /**
     * Empty constructor
     */
    public SkillsetTest() {
        // intentionally left empty
    }
    
    // Methods ...............................................
    
    /**
     * The code to be run before each test is performed
     */
    public void setUp() {
        s = new Skillset(1, 2, 3);
    }
    
    /**
     * Tests the getAgriculture() method
     */
    public void testGetAgriculture() {
        assertEquals(1, s.getAgriculture());
    }
    
    /**
     * Tests the getMedicine() method
     */
    public void testGetMedicine() {
        assertEquals(2, s.getMedicine());
    }
    
    /**
     * Tests the getTechnology() method
     */
    public void testGetTechnology() {
        assertEquals(3, s.getTechnology());
    }
    
    /**
     * Tests the isLessThanOrEqualTo() method when no conditions, 
     * some conditions, and all conditions are met
     */
    public void testIsLessThanOrEqualTo() {
        Skillset a = new Skillset(0, 5, 5);
        assertFalse(s.isLessThanOrEqualTo(a));
        
        Skillset b = new Skillset(5, 0, 5);
        assertFalse(s.isLessThanOrEqualTo(b));
        
        Skillset c = new Skillset(5, 5, 0);
        assertFalse(s.isLessThanOrEqualTo(c));
        
        Skillset d = new Skillset(0, 0, 0);
        assertFalse(s.isLessThanOrEqualTo(d));
        
        Skillset e = new Skillset(5, 5, 5);
        assertTrue(s.isLessThanOrEqualTo(e));
    }
    
    /**
     * Tests the toString() method
     */
    public void testToString() {
        assertEquals("A:1 M:2 T:3", s.toString());
    }
    
    /**
     * Tests the equals() method
     */
    public void testEquals() {
        assertTrue(s.equals(s));
        
        Object n = null;
        assertFalse(s.equals(n));
        
        Object o = new Object();
        assertFalse(s.equals(o));
        
        Skillset a = new Skillset(0, 1, 2);
        assertFalse(s.equals(a));
        
        Skillset b = new Skillset(1, 2, 3);
        assertTrue(s.equals(b));
        
        Skillset c = new Skillset(1, 3, 2);
        assertFalse(s.equals(c));
        
        Skillset d = new Skillset(1, 2, 4);
        assertFalse(s.equals(d));
    }
    
    /**
     * Tests the compareTo() method
     */
    public void testCompareTo() {
        assertEquals(0, s.compareTo(s));
        
        Skillset a = new Skillset(0, 0, 0);
        assertEquals(1, s.compareTo(a));
        assertEquals(-1, a.compareTo(s));
        
        Skillset b = new Skillset(3, 2, 1);
        assertEquals(0, s.compareTo(b));
        assertEquals(0, b.compareTo(s));
    }
    
    
    
}
