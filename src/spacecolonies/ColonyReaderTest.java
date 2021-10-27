// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)

package spacecolonies;
import java.io.FileNotFoundException;
import student.TestCase;

public class ColonyReaderTest extends TestCase {
    
    // Fields .............................................
    
    ColonyReader c;
    
    // Constructor ........................................
    
    /**
     * Empty constructor
     */
    public void ColonyReaderTest() {
        // intentionally left empty
    }
    
    // Methods ............................................
    
    /**
     * The code to be run before each test is performed
     */
    public void setUp() {
        // intentionally left empty
    }
    
    /**
     * tests ColonyReader for FileNotFoundException
     */
    public void testFileNotFound() {
        Exception exception = null;
        try {
            c = new ColonyReader("hello", "");
        }
        catch (Exception e) {
            exception = e;
        }
        assertTrue(exception instanceof FileNotFoundException);
    }
    
    

}
