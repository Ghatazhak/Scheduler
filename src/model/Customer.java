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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(int divisionId) {
        this.divisionId = divisionId;
    }
    @Override
    public String toString(){
        return String.valueOf(customerId);
    }
}
