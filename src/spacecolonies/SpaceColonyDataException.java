// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)
package spacecolonies;

/**
 * This will be thrown if data is incorrect in the input files.
 * 
 * @author aliparslan
 * @version 2021.04.08
 *
 */
public class SpaceColonyDataException extends Exception {
    /**
     * Creates a new SpaceColonyDataException object with a message
     * 
     * @param string
     *            message displayed when this Exception is thrown
     */
    public SpaceColonyDataException(String string) {
        super(string);
    }
}
