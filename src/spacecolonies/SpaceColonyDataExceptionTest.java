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
