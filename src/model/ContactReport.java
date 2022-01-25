package model;

public class ContactReport {
    private String month;
    private String type;
    private int typeAmount;
/** This is the default constructor.
 * @param month
 * @param type
 * @param typeAmount */
    public ContactReport(String month, String type, int typeAmount) {
        this.month = month;
        this.type = type;
        this.typeAmount = typeAmount;
    }
/** This returns the month.
 * @return returns month.*/
    public String getMonth() {
        return month;
    }
/** This method sets the month.
 * @param month */
    public void setMonth(String month) {
        this.month = month;
    }
    /** This method gets type
     * @return String.*/
    public String getType() {
        return type;
    }
    /** This method sets the type.
     * @param type */
    public void setType(String type) {
        this.type = type;
    }
/** This method gets type amount.
 * @return  int*/
    public int getTypeAmount() {
        return typeAmount;
    }
}
