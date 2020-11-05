package ru.stqa.pft.newcontact;

public class ContactData {
    private final String firstname;
    private final String surname;
    private final String address;
    private final String phone;
    private final String email;

    public ContactData(String firstname, String surname, String address, String phone, String email) {
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurname() {
        return surname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
