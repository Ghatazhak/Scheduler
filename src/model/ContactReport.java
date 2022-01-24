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

    public String getMonth() {
        return month;
    }

    public String getType() {
        return type;
    }

    public int getTypeAmount() {
        return typeAmount;
    }
}
