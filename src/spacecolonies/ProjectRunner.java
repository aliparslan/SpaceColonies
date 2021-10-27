package spacecolonies;

import java.io.FileNotFoundException;
import java.text.ParseException;

/**
 * The ProjectRunner class begins the program by creating
 * a ColonyReader and telling it which files to look at.
 * 
 * @author aliparslan
 * @version 2021.03.28
 *
 */
public class ProjectRunner {

    /**
     * Runs the program with given files to read
     * 
     * @param args
     *            the files to read
     * @throws SpaceColonyDataException
     *             if the skills in the file are not in the right range
     * @throws ParseException
     *             if the files in the parameters are not formatted correctly
     * @throws FileNotFoundException
     *             if the given files could not be found
     */
    public static void main(String[] args)
        throws FileNotFoundException,
        ParseException,
        SpaceColonyDataException {
        if (args.length == 2) {
            ColonyReader r = new ColonyReader(args[0], args[1]);
        }
        else {
            ColonyReader r = new ColonyReader("input.txt", "planets.txt");
        }

    }

}
