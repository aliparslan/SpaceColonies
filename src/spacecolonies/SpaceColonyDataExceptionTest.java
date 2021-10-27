// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)
package spacecolonies;
import student.TestCase;

/**
 * Tests the SpaceColonyException
 * @author aliparslan
 * @version 2021.04.11
 *
 */
public class SpaceColonyDataExceptionTest extends TestCase {
    
    /**
     * tests SpaceColonyDataException
     */
    public void testSpaceColonyDataException() {
        Exception e = null;
        try {
            throw new SpaceColonyDataException("hello");
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof SpaceColonyDataException);
    }

}
