package spacecolonies;

/**
 * Stores three integers for skills
 * 
 * @author aliparslan
 * @version 2021.03.28
 *
 */
public class Skillset implements Comparable<Skillset> {

    // Fields .................................................

    private int agriculture;
    private int medicine;
    private int technology;

    // Constructor ............................................

    /**
     * Creates a new Skillset object with three parameters
     * that represent each skill
     * 
     * @param ag
     *            agriculture skill value
     * @param med
     *            medicine skill value
     * @param tech
     *            technology skill value
     */
    public Skillset(int ag, int med, int tech) {
        this.agriculture = ag;
        this.medicine = med;
        this.technology = tech;
    }

    // Methods ................................................


    /**
     * Returns the agriculture skill value
     * 
     * @return the agriculture field
     */
    public int getAgriculture() {
        return this.agriculture;
    }


    /**
     * Returns the medicine skill value
     * 
     * @return the medicine field
     */
    public int getMedicine() {
        return this.medicine;
    }


    /**
     * Returns the technology skill value
     * 
     * @return the technology field
     */
    public int getTechnology() {
        return this.technology;
    }


    /**
     * Compares two Skillset objects and returns if the current object is
     * smaller than or equal to the one being compared to
     * 
     * @param other
     *            Skillset object to be compared to
     * @return if this object is less than or equal to the other object
     */
    public boolean isLessThanOrEqualTo(Skillset other) {
        return this.agriculture <= other.agriculture
            && this.medicine <= other.medicine
            && this.technology <= other.technology;
    }


    /**
     * Returns the Skillset object as a string, including
     * its agriculture, medicine, and technology values
     * 
     * @return a string representing all the skills
     */
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("A:");
        s.append(this.agriculture);
        s.append(" M:");
        s.append(this.medicine);
        s.append(" T:");
        s.append(this.technology);
        return s.toString();
    }


    /**
     * Two Skillset objects are equal if all three fields are the same
     * 
     * @return if the two Skillset objects are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() == obj.getClass()) {
            Skillset s = (Skillset)obj;
            return this.getAgriculture() == s.getAgriculture() && 
                   this.getMedicine() == s.getMedicine() && 
                   this.getTechnology() == s.getTechnology();
        }
        return false;
    }


    /**
     * Returns -1 if this object's sum of its fields is less than the
     * object being compared to, 0 if they are equal, and 1 if it is
     * greater
     * 
     * @param skills 
     *          the Skillset object to be compared to
     *          
     * @return -1, 0, or 1 depending on the inequality of the the two Skillsets
     */
    public int compareTo(Skillset skills) {
        if (this.getAgriculture() + this.getMedicine() + this
            .getTechnology() > skills.getAgriculture() + skills.getMedicine()
                + skills.getTechnology()) {
            return 1;
        }
        else if (this.getAgriculture() + this.getMedicine() + this
            .getTechnology() == skills.getAgriculture() + skills.getMedicine()
                + skills.getTechnology()) {
            return 0;
        }
        return -1;
    }
}
