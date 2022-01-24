package model;

public class Customer {
    private int customerId;
    private String customerName;
    private String address;
    private String phone;
    private String postalCode;
    private int divisionId;
    private String division;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public Customer(int customerId, String customerName, String address, String phone, String postalCode, String division) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.phone = phone;
        this.postalCode = postalCode;
        this.division = division;
    }
    /** This method gets customer id
     * @return  customer id.*/
    public int getCustomerId() {
        return customerId;
    }

    /** This method gets customer name.
     * @return  customer name.*/
    public String getCustomerName() {
        return customerName;
    }
    /** This method gets address.
     * @return  address*/
    public String getAddress() {
        return address;
    }

    /** This method gets postal code.
     * @return  postal code*/
    public String getPostalCode() {
        return postalCode;
    }

    /** This method gets diviison.
     * @return  division name */
    public String getDivision() {
        return division;
    }

    @Override
    public String toString(){
        return String.valueOf(customerId);
    }
}
