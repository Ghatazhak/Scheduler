package model;

public class Country {
   private int countryId;
   private String country;

    public Country(int countryId, String country) {
        this.countryId = countryId;
        this.country = country;
    }

    /** This method gets country name
     * @return Country.*/
    public String getCountry() {
        return country;
    }
    /** This method gets country id.
     * @return  id*/
    public int getCountryId() {
        return countryId;
    }

    @Override
    public String toString(){
        return country;
    }
}
