package model;

public class Contact {
    private int contactId;
    private String contactName;

    public Contact(int contactId, String contactName) {
        this.contactId = contactId;
        this.contactName = contactName;
    }

    /** This method gets contact id.*/
    public int getContactId() {
        return contactId;
    }

    @Override
    public String toString(){
        return contactName;
    }
}
