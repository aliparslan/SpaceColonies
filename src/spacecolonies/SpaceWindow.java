// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who
// do.
// -- Alip Arslan (906347003)
package spacecolonies;

import java.awt.Color;
import cs2.Button;
import cs2.CircleShape;
import cs2.Shape;
import cs2.TextShape;
import cs2.Window;
import cs2.WindowSide;
import list.AList;

/**
 * 
 * SpaceWindow creates and updates the front-end of this simulation, to make a
 * visual representation of the space colonies game. To use this class, just
 * instantiate an object in the ColonyReader Class and pass a ColonyCalculator
 * object. This class is currently hardcoded for 3 planets.
 *
 * @author CS2114 course
 * @version 2020.10.21
 * 
 */
public class SpaceWindow {

    private Window window;
    private ColonyCalculator colonyCalculator;
    private Button accept;
    private Button reject;
    private AList<CircleShape> personCircles;
    private static int DISPLAY_FACTOR = 10;
    private static int PLANET_HEIGHT = 7;
    private static int PLANET_SIZE = PLANET_HEIGHT * DISPLAY_FACTOR;
    private static int QUEUE_STARTX = 50;
    private static int QUEUE_STARTY = 75;
    private static int CIRCLE_SIZE = 4;
    private Shape[] planetShapes = new Shape[3];
    private static Color[] PLANET_COLORS = new Color[7];


    /**
     * Constructor
     * 
     * @param queue
     *            The queue of applicants
     */
    public SpaceWindow(ColonyCalculator colony) {
        window = new Window();
        window.setTitle("Space Colony Placement");
        colonyCalculator = colony;

        // Initialize buttons
        accept = new Button("ACCEPT");
        accept.onClick(this, "clickedAccept");

        reject = new Button("REJECT");
        reject.onClick(this, "clickedReject");

        window.addButton(accept, WindowSide.SOUTH);
        window.addButton(reject, WindowSide.SOUTH);

        // Other
        personCircles = new AList<CircleShape>();

        // COLORS
        PLANET_COLORS[0] = new Color(165, 209, 232);  //blue
        PLANET_COLORS[1] = new Color(173, 147, 189);  //purple
        PLANET_COLORS[2] = new Color(102, 176, 174);  //green
        PLANET_COLORS[3] = new Color(112, 148, 180);  //blue fill
        PLANET_COLORS[4] = new Color(127, 96, 147);   //purple fill
        PLANET_COLORS[5] = new Color(58, 124, 122);   //green fill
        PLANET_COLORS[6] = new Color(49, 86, 119);    //blue dark

        drawCircles();
        initButtons();
        updatePlanet();
        updateText();
        updateButtons();

    }


    /**
     * This method dequeues the Waiting Party at the front of the
     * RollerCoasterQueue
     * queue and updates the window to represent the current status
     * 
     * @param button
     *            seatParty button
     */
    public void clickedAccept(Button button) {
        if (!colonyCalculator.getQueue().isEmpty()) {

            if (colonyCalculator.accept()) {
                update();
            }
            else {
                accept.disable();
            }
        }
        else {
            accept.disable();
            reject.disable();
            endSimulation();
        }
    }


    /**
     * Handles rejection with the Colony Calculator.
     * 
     * @param button
     *            : sendTrain button
     */
    public void clickedReject(Button button) {
        colonyCalculator.reject();
        accept.enable();
        update();
    }


    /**
     * This method disables the buttons and is called when the queue is empty
     */
    private void endSimulation() {
        accept.disable();
        reject.disable();
        window.removeAllShapes();

        // Message
        TextShape simulationStatus = new TextShape(0, 0,
            "All Applicants Processed!");
        int x = (window.getGraphPanelWidth() / 2) - (simulationStatus.getWidth()
            / 2);
        int y = (window.getGraphPanelHeight() / 2);
        simulationStatus.moveTo(x, y);
        simulationStatus.setBackgroundColor(PLANET_COLORS[0]);
        window.addShape(simulationStatus);
    }


    /**
     * This method creates Circle shapes for each Waiting Party in the
     * RollerCoasterQueue queue and adds the circles to the partyCircles list
     * and
     * the window. This is called by the constructor
     */
    private void drawCircles() {
        /**
         * Initialize the window by adding the circles
         */
        int shapeX = QUEUE_STARTX;
        int shapeY = QUEUE_STARTY;

        /**
         * create an object array of queue and create circle shapes for each
         * element in
         * the array and add to the partyCircles list
         */
        Object[] queuedParties = {};
        if (colonyCalculator.getQueue() != null) {
            queuedParties = colonyCalculator.getQueue().toArray();
        }
        for (int i = 0; i < queuedParties.length; i++) {
            int shapeSize = CIRCLE_SIZE * DISPLAY_FACTOR;
            int planetPreference = colonyCalculator.getPlanetIndex(
                ((Person)queuedParties[i]).getPlanetPreference());

            CircleShape shape = new CircleShape(shapeX, shapeY - shapeSize / 2,
                shapeSize);

            switch (planetPreference) {
                case 0:
                    shape.setForegroundColor(PLANET_COLORS[0]);
                    shape.setBackgroundColor(Color.BLACK);
                    break;
                case 1:
                    shape.setForegroundColor(PLANET_COLORS[1]);
                    shape.setBackgroundColor(Color.BLACK);
                    break;
                case 2:
                    shape.setForegroundColor(PLANET_COLORS[2]);
                    shape.setBackgroundColor(Color.BLACK);
                    break;
                default:
                    shape.setForegroundColor(PLANET_COLORS[6]);
                    shape.setBackgroundColor(Color.BLACK);
                    break;
            }

            personCircles.add(shape);
            shapeX = shapeX + shapeSize + 5;
        }

        /**
         * add the circles to the window
         */
        for (int i = 0; i < personCircles.getLength(); i++) {
            window.addShape(personCircles.getEntry(i));
        }
    }


    /**
     * Update everything. Wipes the screen and resets.
     */
    private void update() {
        if (colonyCalculator.getQueue().isEmpty()) {
            endSimulation();
        }
        else {
            window.removeAllShapes();
            updateQueue();
            updatePlanet();
            updateText();
            updateButtons();

        }
    }


    /**
     * This method updates whether the accept button is enabled based on the
     * qualifications of the upcoming person in the queue
     */
    private void updateButtons() {
        reject.enable();
        if (colonyCalculator.getPlanetForPerson(colonyCalculator.getQueue()
            .getFront()) != null)
            accept.enable();
        else
            accept.disable();
    }


    /**
     * This method removes the circle at the front of the queue and in turn
     * calls
     * updateCircles to redraw the new waiting parties
     */
    private void updateQueue() {
        if (!personCircles.isEmpty())
            personCircles.remove(0);
        updateCircles();

    }


    /**
     * This method adds the person circles to the window. This is invoked
     * whenever
     * the accept or reject button is clicked
     */
    private void updateCircles() {
        int shapeX = QUEUE_STARTX;
        for (int i = 0; i < personCircles.getLength(); i++) {
            personCircles.getEntry(i).setX(shapeX);
            window.addShape(personCircles.getEntry(i));
            shapeX = shapeX + personCircles.getEntry(i).getWidth() + 5;

        }
    }


    /**
     * When the window is displayed the first time buttons are disabled if queue
     * is
     * empty otherwise enable
     */
    private void initButtons() {
        if (colonyCalculator.getQueue().isEmpty()) {
            accept.disable();
            reject.disable();
        }
        else {
            accept.enable();
            reject.enable();
        }
    }


    /**
     * This method adds two shapes to the window and updates their sizes based
     * on
     * how full the Coaster train currently is
     */
    private void updatePlanet() {
        final int planetX = (window.getGraphPanelWidth() / 4) - (PLANET_SIZE
            / 2);
        final int planetY = (window.getGraphPanelHeight() * 3 / 4)
            - PLANET_SIZE;
        final int planetOff = (window.getGraphPanelWidth() / 4);

        this.planetShapes[0] = addPlanetShape(planetX, planetY, planetOff, 0);
        this.planetShapes[1] = addPlanetShape(planetX, planetY, planetOff, 1);
        this.planetShapes[2] = addPlanetShape(planetX, planetY, planetOff, 2);

        addPlanetFilled(planetX, planetY, planetOff, 0);
        addPlanetFilled(planetX, planetY, planetOff, 1);
        addPlanetFilled(planetX, planetY, planetOff, 2);
    }


    /**
     * Creates the indicator for a planet
     * 
     * @param planetX
     *            x coordinate of the start of planet indicators
     * @param planetY
     *            y coordinate of the start of planet indicators
     * @param planetOff
     *            distance between planet indicators
     * @param planetIndex
     *            number of planet to represent
     * @return
     */
    private Shape addPlanetShape(
        int planetX,
        int planetY,
        int planetOff,
        int planetIndex) {
        int planetOffValue = (planetIndex) * planetOff;

        Shape planetShape = new Shape(planetX + planetOffValue, planetY,
            PLANET_SIZE, PLANET_SIZE);

        planetShape.setForegroundColor(Color.darkGray);
        planetShape.setBackgroundColor(PLANET_COLORS[planetIndex]);

        window.addShape(planetShape);
        return planetShape;
    }


    /**
     * Creates the filling for planet as people get added
     * 
     * @param planetX
     *            x coordinate of the start of planet indicators
     * @param planetY
     *            y coordinate of the start of planet indicators
     * @param planetOff
     * @param planetIndex
     *            number of planet to update
     */
    private void addPlanetFilled(
        int planetX,
        int planetY,
        int planetOff,
        int planetIndex) {

        Planet planetObject = colonyCalculator.getPlanets()[planetIndex];
        if (planetObject.getCapacity() < 1) {
            System.out.println("capacity was 0 for planet " + (planetIndex));
        }
        else {
            int planetFull = (PLANET_SIZE / planetObject.getCapacity())
                * planetObject.getPopulationSize();
            int planetEmpty = PLANET_SIZE - planetFull;
            int planetOffValue = (planetIndex) * planetOff;

            Shape planetFilled = new Shape(planetX + planetOffValue, planetEmpty
                + planetY, PLANET_SIZE, planetFull);
            planetFilled.setForegroundColor(Color.darkGray);
            planetFilled.setBackgroundColor(PLANET_COLORS[planetIndex + 3]);

            if (planetFull != 0) {
                window.addShape(planetFilled);
                window.moveToFront(planetFilled);
            }
        }
    }


    /**
     * Rewrites the text boxes
     */
    private void updateText() {
        updatePersonText();
        updatePlanetText();
    }


    /**
     * Rewrites the text for the planets
     */
    private void updatePlanetText() {
        int planetY = planetShapes[0].getY() + PLANET_SIZE + DISPLAY_FACTOR;

        textPlanet(planetShapes[0].getX(), planetY, 0);
        textPlanet(planetShapes[1].getX(), planetY, 1);
        textPlanet(planetShapes[2].getX(), planetY, 2);
    }


    /**
     * Creates the string for the planet text
     * 
     * @param planetX
     *            x coordinate for the current planet
     * @param planetY
     *            y coordinate for the current planet
     * @param index
     *            the current planet
     */
    private void textPlanet(int planetX, int planetY, int index) {
        Planet planetObject = colonyCalculator.getPlanets()[index];

        String planetString = planetObject.getName() + ", " + planetObject
            .getPopulationSize() + "/" + planetObject.getCapacity();
        addTextShape(planetString, planetX, planetY);

        int agri = planetObject.getSkills().getAgriculture();
        int medi = planetObject.getSkills().getMedicine();
        int tech = planetObject.getSkills().getTechnology();

        String reqString = "A:" + agri + ", M:" + medi + ", T:" + tech;
        addTextShape(reqString, planetX, planetY + 20);
    }


    /**
     * Updates the text for the current person
     */
    private void updatePersonText() {
        Person nextPerson = colonyCalculator.getQueue().getFront();
        if (nextPerson != null) {
            addTextShape(nextPerson.toString(), 10, 10);
        }
        else {
            addTextShape("nextPerson NULL", 10, 10);
        }
    }


    /**
     * Helper method to add TextShape to the window
     */
    private void addTextShape(String message, int x, int y) {
        if (message != null) {
            TextShape shape = new TextShape(x, y, message, Color.black);
            shape.setBackgroundColor(Color.white);

            window.addShape(shape);
        }
    }

}

