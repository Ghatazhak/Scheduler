package model;

public class ContactReport {
    private String month;
    private String type;
    private int typeAmount;

    public ContactReport(String month, String type, int typeAmount) {
        this.month = month;
        this.type = type;
        this.typeAmount = typeAmount;
    }

    /** This method gets type
     * @return String.*/
    public String getType() {
        return type;
    }

}
