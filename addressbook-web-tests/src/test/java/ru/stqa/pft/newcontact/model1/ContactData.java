package ru.stqa.pft.newcontact.model1;

public class ContactData {
    private final String firstname;
    private final String surname;
    private final String address;
    private final String phone;
    private final String email;
    private String group;

    public ContactData(String firstname, String surname, String address, String phone, String email, String group) {
        this.firstname = firstname;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.group=group;
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

    public String getGroup() { return group; }
      }

