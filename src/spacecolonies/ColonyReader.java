package spacecolonies;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.ParseException;

/**
 * The ColonyReader parses the input data from two text files.
 * It generates the planets and queue of applicants based on one
 * file of comma separated values about applicants and the other
 * about each planet. Then it gives SpaceWindow this queue in
 * order to tie everything together.
 * 
 * @author aliparslan
 * @version 2021.03.28
 *
 */
public class ColonyReader {

    // Fields ......................................................

    private Planet[] planets;
    private ArrayQueue<Person> queue;

    // Constructor .................................................

    /**
     * Reads two files to create a Planet object and an ArrayQueue
     * of Person objects
     * 
     * @param applicantFileName
     *            applicant input file
     * @param planetFileName
     *            planets input file
     * @throws ParseException
     *             if the file was not formatted correctly
     * @throws SpaceColonyDataException
     *             if the skills are not in the correct range
     * @throws FileNotFoundException
     *             if the file could not be found
     * 
     */
    public ColonyReader(String applicantFileName, String planetFileName)
        throws ParseException,
        SpaceColonyDataException,
        FileNotFoundException {

        queue = this.readQueueFile(applicantFileName);
        planets = this.readPlanetFile(planetFileName);
        SpaceWindow window = new SpaceWindow(new ColonyCalculator(queue,
            planets));
    }

    // Methods .....................................................


    /**
     * Reads an input file and creates an array of Planet objects
     * 
     * @param fileName
     *            the file to be read
     * @return array of Planets read from the file
     * @throws ParseException
     *             if the file isn't formatted correctly
     * @throws SpaceColonyDataException
     *             if the skills are not in the correct range
     * @throws FileNotFoundException
     *             if the file could not be found
     */
    private Planet[] readPlanetFile(String fileName)
        throws ParseException,
        SpaceColonyDataException,
        FileNotFoundException {

        Planet[] local = new Planet[ColonyCalculator.NUM_PLANETS];
        Scanner file = new Scanner(new File(fileName));
        int count = 0;
        while (file.hasNextLine()) {
            String[] line = file.nextLine().split(", *");

            if (line.length != 5) {
                file.close();
                throw new ParseException("File is not formatted correctly", 1);
            }

            if (!this.isInSkillRange(Integer.valueOf(line[1]), Integer.valueOf(
                line[2]), Integer.valueOf(line[3]))) {
                file.close();
                throw new SpaceColonyDataException("Skills incorrect");
            }

            local[count] = new Planet(line[0], Integer.valueOf(line[1]), Integer
                .valueOf(line[2]), Integer.valueOf(line[3]), Integer.valueOf(
                    line[4]));
            count++;
        }
        file.close();

        if (local.length < 3) {
            throw new SpaceColonyDataException("Not enough planets");
        }
        return local;
    }


    /**
     * Reads an input file and creates a queue of Person objects
     * 
     * @param fileName
     *            the file to be read
     * @return an ArrayQueue object with Person objects
     *         obtained from the read file
     * 
     * @throws ParseException
     *             if the file isn't formatted correctly
     * @throws SpaceColonyDataException
     *             if the skills are not in the correct range
     * @throws FileNotFoundException
     *             if the file could not be found
     */
    private ArrayQueue<Person> readQueueFile(String fileName)
        throws ParseException,
        SpaceColonyDataException,
        FileNotFoundException {

        ArrayQueue<Person> local = new ArrayQueue<Person>(
            ArrayQueue.DEFAULT_CAPACITY);
        Scanner file = new Scanner(new File(fileName));
        while (file.hasNextLine()) {
            String[] line = file.nextLine().split(", *");

            if (line.length < 4) {
                throw new ParseException("File is not formatted correctly", 1);
            }

            if (!this.isInSkillRange(Integer.valueOf(line[1]), Integer.valueOf(
                line[2]), Integer.valueOf(line[3]))) {
                file.close();
                throw new SpaceColonyDataException("Skills incorrect");
            }

            if (line.length == 4) {
                local.enqueue(new Person(line[0], Integer.valueOf(line[1]),
                    Integer.valueOf(line[2]), Integer.valueOf(line[3]), ""));
            }

            else {
                local.enqueue(new Person(line[0], Integer.valueOf(line[1]),
                    Integer.valueOf(line[2]), Integer.valueOf(line[3]),
                    line[4]));
            }
        }
        return local;
    }


    /**
     * Returns whether or not the parameters are between
     * the minimum and maximum values for a skill
     * 
     * @param num1
     *            agriculture skill
     * @param num2
     *            medicine skill
     * @param num3
     *            technology skill
     * @return if all parameters are within the correct bounds
     */
    private boolean isInSkillRange(int num1, int num2, int num3) {
        return num1 >= ColonyCalculator.MIN_SKILL_LEVEL
            && num1 <= ColonyCalculator.MAX_SKILL_LEVEL
            && num2 >= ColonyCalculator.MIN_SKILL_LEVEL
            && num2 <= ColonyCalculator.MAX_SKILL_LEVEL
            && num3 >= ColonyCalculator.MIN_SKILL_LEVEL
            && num3 <= ColonyCalculator.MAX_SKILL_LEVEL;
    }

}
