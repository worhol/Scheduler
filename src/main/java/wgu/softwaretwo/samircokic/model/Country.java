package wgu.softwaretwo.samircokic.model;

/**
 * <p>This class is the blueprint for the country object</p>
 *
 * @author Samir Cokic
 */
public class Country {
    private int countryId;
    private String countryName;

    /**
     * @param countryId the unique number associated with a country
     * @param countryName the name of the country
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * <p>This method returns unique country id.</p>
     *
     * @return unique country id
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * <p>This method returns the name of the country.</p>
     *
     * @return the name of the country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * <p>This method returns the country name to string.</p>
     *
     * @return country name
     */
    @Override
    public String toString(){
        return getCountryName();
    }
}
