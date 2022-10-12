package wgu.softwaretwo.samircokic.model;

/**
 * <p>This class is the blueprint for the division object</p>
 *
 * @author Samir Cokic
 */
public class Division {
    private int divisionId;
    private String divisionName;
    private int countryId;

    /**
     * @param divisionId unique number associated with division.
     * @param divisionName
     * @param countryId
     */
    public Division(int divisionId, String divisionName, int countryId) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
    }

    /**
     * <p>This method returns the division name to string.</p>
     *
     * @return division name
     */
    @Override
    public String toString(){
        return getDivisionName();
    }

    /**
     * <p>This method returns division id number.</p>
     *
     * @return division id number
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * <p>This method returns division id number.</p>
     *
     * @return division name
     */
    public String getDivisionName() {
        return divisionName;
    }

}
