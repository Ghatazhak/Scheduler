package model;

public class Division {
    private int divisionId;
    private String division;
    private int countryId;

    public Division(int divisionId, String division, int countryId) {
        this.divisionId = divisionId;
        this.division = division;
        this.countryId = countryId;
    }

    /** This method gets division id.
     * @return  division id.*/
    public int getDivisionId() {
        return divisionId;
    }

    /** This method gets division.
     * @return  division name.*/
    public String getDivision() {
        return division;
    }

    /** This method gets country id.
     * @return  country id*/
    public int getCountryId() {
        return countryId;
    }

    @Override
    public String toString(){
        return division;
    }

}
